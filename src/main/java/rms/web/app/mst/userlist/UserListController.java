package rms.web.app.mst.userlist;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import rms.common.utils.BeanUtils;
import rms.common.utils.PageInfo;
import rms.domain.app.mst.userlist.UserListEntityCondition;
import rms.domain.app.mst.userlist.UserListEntityResult;
import rms.domain.app.mst.userlist.UserListService;
import rms.domain.app.shared.entity.SearchResultEntity;
import rms.web.app.mst.userregist.UserRegistController;
import rms.web.app.system.menu.MenuController;

/**
 * ユーザ一覧画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = UserListForm.class)
public class UserListController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/userList";

    /** マッピングURL */
    public static final String MAPPING_URL = "/mst/user/list";

    /** ユーザ情報取得サービス */
    @Autowired
    UserListService service;

    /**
     * ユーザ一覧画面フォームの初期化
     * @return
     */
    @ModelAttribute
    UserListForm setupForm() {
        return new UserListForm();
    }

    /**
     * 初期表示処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "init")
    public String init(UserListForm form,
                       Model model) {
        return PAGE_URL;
    }

    /**
     * 検索処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "search")
    public String search(@Validated UserListForm form,
                         BindingResult bindingResult,
                         Model model) {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // 検索結果・ページ情報の初期化
        form.setPageInfo(new PageInfo());
        form.setResultList(null);

        // 検索条件の生成
        UserListEntityCondition condition = BeanUtils.createCopyProperties(form.getCondition(),
                                                                           UserListEntityCondition.class);

        // 検索処理
        SearchResultEntity<UserListEntityResult> resultEntity = service.search(condition, form.getPageInfo());
        if (resultEntity.getResultList().isEmpty()) {
            // 検索結果が見つかりません
            bindingResult.reject("error.006", message.getMessage("error.006", null, Locale.getDefault()));
            return PAGE_URL;
        }

        // 検索結果をフォームに反映
        form.setResultList(resultEntity.getResultList());
        form.getPageInfo().setTotalSize(resultEntity.getCount());

        return PAGE_URL;
    }

    /**
     * 再検索処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "reSearch")
    public String reSearch(UserListForm form,
                           Model model) {
        logger.debug("入力フォーム情報 -> {}", form);

        // 検索条件の生成
        UserListEntityCondition condition = BeanUtils.createCopyProperties(form.getCondition(),
                                                                           UserListEntityCondition.class);

        // 検索処理
        SearchResultEntity<UserListEntityResult> resultEntity = service.search(condition, form.getPageInfo());

        // 検索結果をフォームに反映
        form.setResultList(resultEntity.getResultList());
        form.getPageInfo().setTotalSize(resultEntity.getCount());

        return PAGE_URL;
    }

    /**
     * 前ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pagePrev")
    public String pagePrev(UserListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 次ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pageNext")
    public String pageNext(UserListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().next();

        return redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 戻る処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back(UserListForm form,
                       Model model) {
        // メニュー画面に遷移
        return redirect(MenuController.MAPPING_URL);
    }

    /**
     * ユーザ新規登録画面遷移処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "insert")
    public String selectInsert() {
        return redirect(UserRegistController.MAPPING_URL, "initInsert");
    }

    /**
     * ユーザ選択処理
     * @param form
     * @param index
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{index}", params = "select")
    public String select(UserListForm form,
                         @PathVariable int index,
                         Model model) {
        logger.debug("選択値 -> {}", index);

        // 選択したユーザ情報
        UserListEntityResult user = form.getResultList().get(index);
        logger.debug("選択ユーザ情報 -> {}", user);

        return redirect(UserRegistController.MAPPING_URL + "/" + user.getUserId(), "initUpdate");
    }
}
