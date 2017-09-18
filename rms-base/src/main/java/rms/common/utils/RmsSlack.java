package rms.common.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

/**
 * Slack関連クラス
 * @author
 */
@Component
public class RmsSlack {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(RmsSlack.class);

    /** token */
    private static final String token = ProjectPropertiesStaticAccessor.properties.getSlackToken();

    /** session */
    SlackSession session = null;

    /**
     * Slack通知<br>
     * （synchronized）
     * @param info
     * @return 結果 [true:送信成功 false:送信失敗]
     */
    public synchronized boolean send(RmsSlackInfo info) {

        // 接続
        connect();
        if (session == null || session.isConnected()) {
            // 接続失敗
            return false;
        }

        // ユーザの取得
        SlackUser user = session.findUserByEmail(info.getMail());
        if (user == null) {
            // ユーザーの取得失敗
            logger.info("slack user not found -> {}", info.getMail());
            return false;
        }

        // メッセージ送信
        session.sendMessageToUser(user, info.getText(), null);
        // 送信成功
        logger.info("slack send successs -> {} {}", user.getRealName(), user.getUserMail());

        return true;
    }

    /**
     * 接続<br>
     * （切断はTimeoutで自動的に行う）
     */
    private void connect() {
        try {
            if (session == null || !session.isConnected()) {
                session = SlackSessionFactory.createWebSocketSlackSession(token);
                session.connect();
                logger.info("slack connect new");
            }
        } catch (IOException e) {
            logger.warn("slack connect error", e);
        }
    }
}
