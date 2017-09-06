package rms.common.abstracts;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import rms.common.utils.MessageSourceEnumAccessor;
import rms.common.utils.ProjectProperties;
import rms.common.utils.RmsSessionInfo;
import rms.common.utils.RmsSessionUtils;
import rms.common.utils.UrlCreateHelper;

/**
 * AbstractController
 * @author
 */
public abstract class AbstractController {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    /** MessageSource */
    @Autowired
    protected MessageSourceEnumAccessor message;

    /** application.properties */
    @Autowired
    protected ProjectProperties properties;

    /** UrlCreateHelper */
    @Autowired
    protected UrlCreateHelper urlHelper;

    /**
     * 画面IDの取得
     * @return
     */
    protected abstract String getScreenId();

    // TODO ここにこの処理を書くのは微妙・・・
    /**
     * 画面ID・前画面IDの保存
     * @param session
     */
    @ModelAttribute
    protected void saveScreenId(HttpSession session) {
        // アプリケーション固有セッション情報の取得
        RmsSessionInfo rmsSessionInfo = RmsSessionUtils.getRmsSessionInfo(session);

        String newScreenId = getScreenId();
        String screenId = rmsSessionInfo.getScreenId();

        // 画面IDの設定
        rmsSessionInfo.setScreenId(newScreenId);
        // 前画面IDの設定
        if (!newScreenId.equals(screenId)) {
            rmsSessionInfo.setPreScreenId(screenId);
        }
    }
}
