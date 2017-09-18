package rms.common.utils;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * メール関連クラス
 * @author
 */
@Component
public class RmsMail {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(RmsMail.class);

    /** メール関連Helper */
    @Autowired
    private RmsMailHelper helper;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * メール送信
     * @param mailInfoList
     * @return 結果 [true:送信成功 false:送信失敗]
     * @throws MessagingException
     */
    public boolean send(List<RmsMailInfo> mailInfoList) throws MessagingException {
        return send(helper.createMimeMessage(mailInfoList));
    }

    /**
     * メール送信
     * @param mailInfo
     * @return 結果 [true:送信成功 false:送信失敗]
     * @throws MessagingException
     */
    public boolean send(RmsMailInfo mailInfo) throws MessagingException {
        return send(helper.createMimeMessage(mailInfo));
    }

    /**
     * メール送信
     * @param message
     * @return 結果 [true:送信成功 false:送信失敗]
     */
    public boolean send(SimpleMailMessage message) {
        return send(new SimpleMailMessage[] { message });
    }

    /**
     * メール送信
     * @param message
     * @return 結果 [true:送信成功 false:送信失敗]
     */
    public boolean send(SimpleMailMessage... message) {
        boolean b = false;
        try {
            // メール送信
            mailSender.send(message);
            b = true;
            logger.info("mail send successs");
        } catch (Exception e) {
            logger.warn("mail send error", e);
        }

        return b;
    }

    /**
     * メール送信
     * @param message
     * @return 結果 [true:送信成功 false:送信失敗]
     */
    public boolean send(MimeMessage message) {
        return send(new MimeMessage[] { message });
    }

    /**
     * メール送信
     * @param message
     * @return 結果 [true:送信成功 false:送信失敗]
     */
    public boolean send(MimeMessage... message) {
        boolean b = false;
        try {
            // メール送信
            mailSender.send(message);
            b = true;
            logger.debug("mail send successs");
        } catch (Exception e) {
            logger.warn("mail send error", e);
        }

        return b;
    }

}
