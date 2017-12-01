package rms.web.app.mst.userlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rms.common.consts.MRoleConst;
import rms.common.consts.MessageEnum;
import rms.common.consts.MessageTypeConst;
import rms.common.utils.RmsBeanUtils;
import rms.common.utils.SearchResultDto;
import rms.domain.app.mst.userlist.UserListDtoCondition;
import rms.domain.app.mst.userlist.UserListEntityResult;
import rms.domain.app.mst.userlist.UserListService;
import rms.web.app.mst.userregist.UserRegistController;
import rms.web.app.system.menu.MenuController;

/**
 * ユーザ一覧画面コントローラー<br>
 * 役割：管理者のみ
 * @author
 */
@Controller
@SessionAttributes(types = UserListForm.class)
@Secured(value = { MRoleConst.ADMIN })
public class UserListController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/userList";

    /** マッピングURL */
    public static final String MAPPING_URL = "/mst/userlist";

    /** 画面ID */
    public static final String SCREEN_ID = "M001";

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

        // ページ情報の初期化
        form.getPageInfo().clear();

        // 検索条件の生成
        UserListDtoCondition condition = RmsBeanUtils.createCopyProperties(form.getCondition(),
                                                                           UserListDtoCondition.class);

        // 検索処理
        SearchResultDto<UserListEntityResult> resultDto = service.search(condition, form.getPageInfo());

        // 検索結果をフォームに反映
        form.setResultList(resultDto.getResultList());
        form.getPageInfo().setTotalSize(resultDto.getCount());

        if (resultDto.getResultList().isEmpty()) {
            // 「検索結果が見つかりません」
            model.addAttribute(MessageTypeConst.ERROR, message.getMessage(MessageEnum.error006));
            return PAGE_URL;
        }

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

        if (form.getPageInfo().getTotalSize() == 0) {
            // 検索実績なしのため、再検索を行わない
            return PAGE_URL;
        }

        // 検索条件の生成
        UserListDtoCondition condition = RmsBeanUtils.createCopyProperties(form.getCondition(),
                                                                           UserListDtoCondition.class);

        // 検索処理
        SearchResultDto<UserListEntityResult> resultDto = service.search(condition, form.getPageInfo());

        // 検索結果をフォームに反映
        form.setResultList(resultDto.getResultList());
        form.getPageInfo().setTotalSize(resultDto.getCount());

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

        return urlHelper.redirect(MAPPING_URL, "reSearch");
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

        return urlHelper.redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 戻る処理
     * @param sessionStatus
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back(SessionStatus sessionStatus) {
        // セッション破棄
        sessionStatus.setComplete();
        return urlHelper.redirect(MenuController.MAPPING_URL);
    }

    /**
     * ユーザ新規登録画面遷移処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "insert")
    public String selectInsert() {
        return urlHelper.redirect(UserRegistController.MAPPING_URL, "initInsert");
    }

    /**
     * ユーザ選択処理
     * @param form
     * @param index
     * @param attributes
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{index}", params = "select")
    public String select(UserListForm form,
                         @PathVariable int index,
                         RedirectAttributes attributes,
                         Model model) {
        logger.debug("選択値 -> {}", index);

        // 選択したユーザ情報
        UserListEntityResult userEntity = form.getResultList().get(index);
        logger.debug("選択ユーザ情報 -> {}", userEntity);

        attributes.addFlashAttribute("userId", userEntity.getUserId());
        return urlHelper.redirect(UserRegistController.MAPPING_URL, "initUpdate");
    }

    /*
     * (非 Javadoc)
     * @see rms.common.abstracts.AbstractController#getScreenId()
     */
    @Override
    protected String getScreenId() {
        return SCREEN_ID;
    }

}
