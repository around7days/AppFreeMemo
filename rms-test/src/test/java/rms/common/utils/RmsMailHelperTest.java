package rms.common.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class)
public class RmsMailHelperTest {

    @Autowired
    RmsMailHelper helper;

    @Test
    public final void test_createMimeMessage_1件() throws MessagingException, IOException {
        RmsMailInfo mailInfo = new RmsMailInfo();
        mailInfo.setFromAddr("from@test.co.jp");
        mailInfo.addToAddr("to1@test.co.jp");
        mailInfo.addToAddr("to2@test.co.jp");
        mailInfo.addCcAddr("cc1@test.co.jp");
        mailInfo.addCcAddr("cc2@test.co.jp");
        mailInfo.addBccAddr("bcc1@test.co.jp");
        mailInfo.addBccAddr("bcc2@test.co.jp");
        mailInfo.setSubject("タイトル");
        mailInfo.setText("テキスト");
        mailInfo.setFileNm("ファイル名");
        mailInfo.setFilePath("dummy/dummy.txt");

        MimeMessage message = helper.createMimeMessage(mailInfo);
        assertThat(message.getFrom()[0], is(new InternetAddress("from@test.co.jp"))); // From
        Address[] allAddress = message.getAllRecipients();
        assertThat(allAddress[0], is(new InternetAddress("to1@test.co.jp"))); // to1
        assertThat(allAddress[1], is(new InternetAddress("to2@test.co.jp"))); // to2
        assertThat(allAddress[2], is(new InternetAddress("cc1@test.co.jp"))); // cc1
        assertThat(allAddress[3], is(new InternetAddress("cc2@test.co.jp"))); // cc2
        assertThat(allAddress[4], is(new InternetAddress("bcc1@test.co.jp"))); // bcc1
        assertThat(allAddress[5], is(new InternetAddress("bcc2@test.co.jp"))); // bcc2
        assertThat(message.getSubject(), is("タイトル")); // タイトル
        // assertThat(message.getContent().toString(), is("テキスト")); // テキスト
        // assertThat(message.getFileName(), is("ファイル名")); // ファイル名
        // assertThat(message.get(), is()); // ファイル
    }

    @Test
    public final void test_createMimeMessage_複数件() throws MessagingException {
        List<RmsMailInfo> list = new ArrayList<>();
        {
            RmsMailInfo mailInfo = new RmsMailInfo();
            mailInfo.setFromAddr("from1@test.co.jp");
            mailInfo.addToAddr("to@test.co.jp");
            mailInfo.setSubject("タイトル");
            mailInfo.setText("テキスト");
            mailInfo.setFileNm("ファイル名");
            mailInfo.setFilePath("dummy/dummy.txt");
            list.add(mailInfo);
        }
        {
            RmsMailInfo mailInfo = new RmsMailInfo();
            mailInfo.setFromAddr("from2@test.co.jp");
            mailInfo.addToAddr("to@test.co.jp");
            mailInfo.setSubject("タイトル");
            mailInfo.setText("テキスト");
            mailInfo.setFileNm("ファイル名");
            mailInfo.setFilePath("dummy/dummy.txt");
            list.add(mailInfo);
        }

        MimeMessage[] messages = helper.createMimeMessage(list);
        assertThat(messages[0].getFrom()[0], is(new InternetAddress("from1@test.co.jp"))); // From
        assertThat(messages[1].getFrom()[0], is(new InternetAddress("from2@test.co.jp"))); // From
    }
}
