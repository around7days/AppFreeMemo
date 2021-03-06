package rms.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringWebApplication;
import rms.testutil.mock.RmsMockFileType;
import rms.testutil.mock.RmsMockUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, properties = "spring.profiles.active=test")
public class RmsMailTest {

    @Autowired
    RmsMail mail;

    @Test
    public void test_send_メール送信_単体_添付ファイル無し() throws MessagingException {
        RmsMailInfo mailInfo = new RmsMailInfo();
        mailInfo.setFromAddr("from@xxx.com")
                .addToAddr("to@xxx.com")
                .addCcAddr("cc@xxx.com")
                .addBccAddr("bcc@xxx.com")
                .setSubject("Spring bootメール送信テスト")
                .setText("メール送信_単体_添付ファイル無し");

        mail.send(mailInfo);
    }

    @Test
    public void test_send_メール送信_複数_添付ファイル無し() throws MessagingException {
        List<RmsMailInfo> list = new ArrayList<>();

        {
            RmsMailInfo mailInfo = new RmsMailInfo();
            mailInfo.setFromAddr("from@xxx.com")
                    .addToAddr("to@xxx.com")
                    .setSubject("Spring bootメール送信テスト")
                    .setText("メール送信_複数_添付ファイル無し その1");
            list.add(mailInfo);
        }
        {
            RmsMailInfo mailInfo = new RmsMailInfo();
            mailInfo.setFromAddr("from@xxx.com")
                    .addToAddr("to@xxx.com")
                    .setSubject("Spring bootメール送信テスト")
                    .setText("メール送信_複数_添付ファイル無し その2");
            list.add(mailInfo);
        }

        mail.send(list);
    }

    @Test
    public void test_send_メール送信_単体_添付ファイルあり() throws MessagingException {
        String fileNm = "丑 丞 乃 之 乎 也 云 亘‐亙 些 亦 　 髙 ～ ①.xlsx";
        String filePath = RmsMockUtils.getDummyFilePath(RmsMockFileType.TXT);

        RmsMailInfo mailInfo = new RmsMailInfo();
        mailInfo.setFromAddr("from@xxx.com")
                .addToAddr("to@xxx.com")
                .setSubject("Spring bootメール送信テスト")
                .setText("メール送信_単体_添付ファイル無し")
                .setFileNm(fileNm)
                .setFilePath(filePath);

        mail.send(mailInfo);
    }
}
