package rms.web.mst.user.regist;

import java.util.Locale;

import rms.com.base.BusinessException;
import rms.com.consts.MessageConst;
import rms.domain.com.entity.MUser;
import rms.domain.mst.user.service.UserServiceRegist;
import rms.domain.mst.user.service.UserServiceSelect;
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
public class UserRegistController extends rms.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserRegistController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/userRegist";

    /** マッピングURL */
    public static final String MAPPING_URL = "/mst/user/regist";

    /** ユーザ情報取得サービス */
    @Autowired
    UserServiceSelect userServiceSelect;

    /** ユーザ情報登録サービス */
    @Autowired
    UserServiceRegist userServiceRegist;

    /** ユーザ登録画面フォーム */
    @ModelAttribute
    UserRegistForm setupForm() {
        return new UserRegistForm();
    }

    /**
     * 初期表示処理（新規時）
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "initInsert")
    public String initInsert(Model model) {
        // フォーム情報の新規生成
        UserRegistForm form = setupForm();

        // 初期値の設定
        // 画面表示モードを「新規」に設定
        form.setViewMode(UserRegistForm.VIEW_MODE_INSERT);
        // XXX ダミー値
        form.setUserId("user01");
        form.setPassword("x");
        form.setUserNm("x");

        // フォーム情報の格納
        model.addAttribute(form);

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
        // TODO やり直し
        // 画面表示モードを「更新」に設定
        form.setViewMode(UserRegistForm.VIEW_MODE_UPDATE);

        // 更新初期画面表示情報の取得
        MUser mUser = userServiceSelect.getUserInfo(userId);

        // 取得した情報をフォームに反映
        BeanUtils.copyProperties(mUser, form);

        logger.debug("フォーム情報 -> {}", form.toString());

        return PAGE_URL;
    }

    /**
     * 新規登録処理
     * @param form
     * @param bindingResult
     * @param redirectAttr
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "insert")
    public String insert(@Validated(UserRegistForm.Insert.class) UserRegistForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttr,
                         Model model) {
        // TODO やり直し

        logger.debug("フォーム情報 -> {}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return PAGE_URL;
        }

        //        /*
        //         * ユーザマスタ
        //         */
        //        // ユーザマスタ登録情報の生成
        //        MUser mUser = new MUser();
        //        BeanUtils.copyProperties(form, mUser);
        //
        //        // ユーザマスタ登録処理
        //        userService.insertUser(mUser);
        //
        //        /*
        //         * ユーザ役割マスタ
        //         */
        //        // ユーザ役割マスタ更新情報の生成
        //        MUserRole mUserRole = new MUserRole();
        //        BeanUtils.copyProperties(form, mUserRole);
        //
        //        // ユーザ役割マスタ更新処理
        //        userService.updateUser(mUser);
        //
        //        // 登録情報の生成
        //        MUser mUser = new MUser();
        //        BeanUtils.copyProperties(form, mUser);
        //
        //        // 登録処理
        //        userService.insert(mUser);

        // TODO MessageResorceが使いにくい。どこかで改良。
        // TODO 完了メッセージをどこかで定数にする。
        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageConst.SUCCESS, message.getMessage("info.001", null, Locale.getDefault()));

        return redirect(UserSearchController.MAPPING_URL, "reSearch");
    }

    /**
     * 更新処理
     * @param form
     * @param bindingResult
     * @param redirectAttr
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "update")
    public String update(@Validated(UserRegistForm.Update.class) UserRegistForm form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttr,
                         Model model) {
        // TODO フォームでリクエスト情報を受け取る場合に、ユーザーID等の想定外の情報まで受け取る可能性があるのが気になる。
        // TODO やり直し
        logger.debug("フォーム情報 -> {}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return PAGE_URL;
        }

        // 完了メッセージ
        redirectAttr.addFlashAttribute(MessageConst.SUCCESS, message.getMessage("info.002", null, Locale.getDefault()));

        return redirect(UserSearchController.MAPPING_URL, "reSearch");
    }

    /**
     * 戻る処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back() {
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
