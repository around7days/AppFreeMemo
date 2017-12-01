package rms.common.utils;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class)
public class RmsSlackTest {

    @Autowired
    RmsSlack slack;

    @Test
    public void slack接続失敗() throws MessagingException, IOException {
        RmsSlackInfo info = new RmsSlackInfo();
        info.setMail("");
        info.setText("hello world!");
        boolean b = slack.send(info);
        assertFalse(b);
    }
}
