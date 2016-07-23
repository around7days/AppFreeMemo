package rms.web.mst.user.regist;

import java.util.List;
import java.util.Locale;

import rms.com.base.BusinessException;
import rms.com.consts.MessageConst;
import rms.domain.com.entity.MUser;
import rms.domain.mst.user.entity.UserEntity;
import rms.domain.mst.user.service.UserRegistService;
import rms.domain.mst.user.service.UserSelectService;
import rms.web.com.utils.SelectOptionEntity;
import rms.web.mst.user.search.UserSearchController;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.seasar.doma.jdbc.OptimisticLockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * ユーザ登録画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = UserRegistForm.class)
public class UserRegistController extends rms.web.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserRegistController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/userRegist";

    /** マッピングURL */
    public static final String MAPPING_URL = "/mst/user/regist";

    /** ユーザ情報取得サービス */
    @Autowired
    UserSelectService userSelectService;

    /** ユーザ情報登録サービス */
    @Autowired
    UserRegistService userRegistService;

    /**
     * ユーザ登録画面フォームの初期化
     * @return
     */
    @ModelAttribute
    UserRegistForm setupForm() {
        UserRegistForm form = new UserRegistForm();

        // selectbox用 承認者一覧の取得
        List<SelectOptionEntity> approverList = userSelectService.getSelectboxApprover();
        form.setApproverList(approverList);

        return form;
    }

    /**
     * 初期表示処理（新規時）
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "initInsert")
    public String initInsert(UserRegistForm form,
                             Model model) {
        // 画面表示モードを「新規」に設定
        form.setViewMode(UserRegistForm.VIEW_MODE_INSERT);

        return PAGE_URL;
    }

    /**
     * 初期表示処理（更新時）
     * @param form
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/{userId}", params = "initUpdate")
    public String initUpdate(UserRegistForm form,
                             @PathVariable String userId,
                             Model model) {
        // 画面表示モードを「更新」に設定
        form.setViewMode(UserRegistForm.VIEW_MODE_UPDATE);

        // 更新初期画面表示情報の取得
        UserEntity userEntity = userSelectService.getUserInfo(userId);

        // 取得した情報をフォームに反映
        // TODO ちょい手抜きで・・・
        BeanUtils.copyProperties(userEntity, form);
        BeanUtils.copyProperties(userEntity.getMUser(), form);

        logger.debug("フォーム情報 -> {}", form.toString());

        return PAGE_URL;
    }

    /**
     * 新規登録処理
     * @param form
     * @param bindingResult
     * @param sessionStatus
     * @param redirectAttr
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = MAPPING_URL, params = "insert")
    public String insert(@Validated(UserRegistForm.Insert.class) UserRegistForm form,
                         BindingResult bindingResult,
                         SessionStatus sessionStatus,
                         RedirectAttributes redirectAttr,
                         Model model) throws BusinessException {
        logger.debug("フォーム情報 -> {}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return PAGE_URL;
        }

        /*
         * 業務ロジックチェック
         */
        // ユーザIDの重複チェック
        userSelectService.checkUniquUserId(form.getUserId());
        // 承認ルート設定チェック
        userSelectService.checkApprovalRoute(form.getRoleApplicantFlg(), form.getApprover3Id());

        /*
         * ユーザマスタ
         */
        // ユーザマスタ登録情報の生成
        MUser mUser = new MUser();
        BeanUtils.copyProperties(form, mUser);
        // ユーザマスタ登録処理
        userRegistService.insertUserMst(mUser);

        /*
         * ユーザ役割マスタ
         */
        // ユーザ役割マスタ登録処理
        userRegistService.deleteInsertUserRoleMst(form.getUserId(),
                                                  form.getRoleApplicantFlg(),
                                                  form.getRoleApproverFlg(),
                                                  form.getRoleAdminFlg());

        // TODO MessageResorceが使いにくい。どこかで改良。
        // TODO 完了メッセージをどこかで定数にする。
        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageConst.SUCCESS, message.getMessage("info.001", null, Locale.getDefault()));
        // セッション破棄
        sessionStatus.setComplete();

        return redirect(UserSearchController.MAPPING_URL, "reSearch");
    }

    /**
     * 更新処理
     * @param form
     * @param bindingResult
     * @param sessionStatus
     * @param redirectAttr
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = MAPPING_URL, params = "update")
    public String update(@Validated(UserRegistForm.Update.class) UserRegistForm form,
                         BindingResult bindingResult,
                         SessionStatus sessionStatus,
                         RedirectAttributes redirectAttr,
                         Model model) throws BusinessException {
        // TODO フォームでリクエスト情報を受け取る場合に、ユーザーID等の想定外の情報まで受け取る可能性があるのが気になる。
        logger.debug("フォーム情報 -> {}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return PAGE_URL;
        }

        // TODO 別のところに持って行けへんかな？
        /*
         * 業務ロジックチェック
         */
        // 承認ルート設定チェック
        userSelectService.checkApprovalRoute(form.getRoleApplicantFlg(), form.getApprover3Id());

        /*
         * ユーザマスタ
         */
        // ユーザマスタ更新情報の生成
        MUser mUser = new MUser();
        BeanUtils.copyProperties(form, mUser);
        // ユーザマスタ登録処理
        userRegistService.updateUserMst(mUser);

        /*
         * ユーザ役割マスタ
         */
        // ユーザ役割マスタ登録処理
        userRegistService.deleteInsertUserRoleMst(form.getUserId(),
                                                  form.getRoleApplicantFlg(),
                                                  form.getRoleApproverFlg(),
                                                  form.getRoleAdminFlg());

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageConst.SUCCESS, message.getMessage("info.002", null, Locale.getDefault()));
        // セッション破棄
        sessionStatus.setComplete();

        return redirect(UserSearchController.MAPPING_URL, "reSearch");
    }

    /**
     * 戻る処理
     * @param form
     * @param sessionStatus
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back(UserRegistForm form,
                       SessionStatus sessionStatus) {
        // セッション破棄
        sessionStatus.setComplete();
        return redirect(UserSearchController.MAPPING_URL, "reSearch");
    }

    // ----------------------------------------------------------------------------------------
    /**
     * 業務エラー（BusinessException）のエラーハンドリング
     * @param e
     * @param session
     * @param model
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public String handlerException(BusinessException e,
                                   HttpSession session,
                                   Model model) {
        logger.debug("業務エラー -> {}", e.getErrorMessage());

        // メッセージを反映
        model.addAttribute(MessageConst.ERROR, e.getErrorMessage());
        // セッションからフォーム情報を取得して反映
        model.addAttribute(getSessionForm(session, UserRegistForm.class));

        return PAGE_URL;
    }

    /**
     * 楽観的排他制御（OptimisticLockException）のエラーハンドリング
     * @param e
     * @param session
     * @param model
     * @return
     */
    @ExceptionHandler(OptimisticLockException.class)
    public String handlerException(OptimisticLockException e,
                                   HttpSession session,
                                   Model model) {
        logger.debug("楽観的排他制御エラー -> {}", e.getMessage());

        // メッセージとフォーム情報を反映
        model.addAttribute(MessageConst.ERROR, message.getMessage("error.002", null, Locale.getDefault()));
        // セッションからフォーム情報を取得して反映
        model.addAttribute(getSessionForm(session, UserRegistForm.class));

        return PAGE_URL;
    }

    // ----------------------------------------------------------------------------------------
}
