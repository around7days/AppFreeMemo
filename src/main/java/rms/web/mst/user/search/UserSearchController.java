package rms.web.mst.user.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import rms.com.consts.PageIdConst;
import rms.com.doma.entity.MUser;

/**
 * ユーザ一覧画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = UserSearchForm.class)
public class UserSearchController extends rms.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserSearchController.class);

    /** デフォルトマッピングURL */
    public static final String DEFAULT_URL = "/mst/user/search";

    /** デフォルトページID */
    private static final String DEFAULT_PAGE = PageIdConst.MST_USER_SEARCH;

    /** ユーザ一覧画面サービス */
    @Autowired
    UserSearchService userSearchService;

    /** ユーザ一覧画面フォーム */
    @ModelAttribute(value = "userSearchForm")
    UserSearchForm setupForm() {
        return new UserSearchForm();
    }

    /**
     * 初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "init")
    public String init(UserSearchForm form,
                       Model model) {
        // 初期値設定

        return DEFAULT_PAGE;
    }

    /**
     * 検索処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "search")
    public String search(@Validated UserSearchForm form,
                         BindingResult bindingResult,
                         Model model) {
        logger.debug("フォーム情報 -> {}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return DEFAULT_PAGE;
        }

        // 検索処理
        userSearchService.search(form);
        if (form.getResultList().isEmpty()) {
            bindingResult.reject("", "検索結果は存在しません");
            return DEFAULT_PAGE;
        }

        return DEFAULT_PAGE;
    }

    /**
     * 再検索処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "reSearch")
    public String reSearch(UserSearchForm form,
                           Model model) {
        logger.debug("フォーム情報 -> {}", form.toString());

        // 検索処理
        userSearchService.search(form);

        return DEFAULT_PAGE;
    }

    /**
     * 前ページング処理<br>
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "pagePrev")
    public String pagePrev(UserSearchForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return redirect("/mst/user/search", "reSearch");
    }

    /**
     * 次ページング処理<br>
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "pageNext")
    public String pageNext(UserSearchForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().next();

        return redirect("/mst/user/search", "reSearch");
    }

    /**
     * ユーザ新規処理
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "insert")
    public String selectInsert() {
        return redirect("/mst/user/regist", "initInsert");
    }

    /**
     * ユーザ選択処理
     * @param form
     * @param index
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL + "/{index}", params = "select")
    public String select(UserSearchForm form,
                         @PathVariable int index,
                         Model model) {
        logger.debug("選択値 -> {}", index);

        // 選択したユーザ情報
        MUser user = form.getResultList().get(index);
        logger.debug("選択ユーザ情報 -> {}", user.toString());

        return redirect("/mst/user/regist/" + user.getUserId(), "initUpdate");
    }
}
