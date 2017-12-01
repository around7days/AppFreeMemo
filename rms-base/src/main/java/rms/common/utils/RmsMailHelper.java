package rms.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * メール関連Helperクラス
 * @author
 */
@Component
public class RmsMailHelper {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(RmsMailHelper.class);

    @Autowired
    JavaMailSender mailSender;

    // @Autowired
    // ProjectProperties properties;

    /**
     * メッセージ作成
     * @param mailInfoList
     * @return
     * @throws MessagingException
     */
    public MimeMessage[] createMimeMessage(List<RmsMailInfo> mailInfoList) throws MessagingException {
        List<MimeMessage> list = new ArrayList<>();
        for (RmsMailInfo mailInfo : mailInfoList) {
            MimeMessage message = createMimeMessage(mailInfo);
            list.add(message);
        }
        return list.toArray(new MimeMessage[list.size()]);
    }

    /**
     * メッセージ作成
     * @param mailInfo
     * @return
     * @throws MessagingException
     */
    public MimeMessage createMimeMessage(RmsMailInfo mailInfo) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, message.getEncoding());

        helper.setFrom(mailInfo.getFromAddr());
        helper.setTo(mailInfo.getToAddrs());
        helper.setCc(mailInfo.getCcAddrs());
        helper.setBcc(mailInfo.getBccAddrs());
        helper.setSubject(mailInfo.getSubject());
        helper.setText(mailInfo.getText());

        if (mailInfo.getFileNm() != null && mailInfo.getFile() != null) {
            helper.addAttachment(mailInfo.getFileNm(), mailInfo.getFile());
        }

        logger.debug("mailInfo -> {}", mailInfo);
        return helper.getMimeMessage();
    }

}
