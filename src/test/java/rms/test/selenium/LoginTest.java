package rms.test.selenium;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import rms.test.selenium.page.ログイン画面;
import selenium.base.AbstractSeleniumTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginTest extends AbstractSeleniumTest {

    @LocalServerPort
    private int port;

    @Value("${server.context-path}")
    private String serverContextPath;

    @Before
    public void setup() {
        // ログイン画面表示までの初期処理
        helper.url("http://localhost:" + port + serverContextPath + "/login");
    }

    @Test
    public void ログイン画面表示() throws IOException {
        capture.screenShot();
        assertThat(helper.getTitle(), is("ログイン画面"));
    }

    @Test
    public void ログイン失敗_ID間違え() throws IOException {

        ログイン画面 page = new ログイン画面().initialize(driver);
        helper.setKeys(page.ユーザID(), "xxxx01");
        helper.setKeys(page.パスワード(), "pass");
        capture.screenShot();
        page.ログインボタン().click();

        // ログイン画面で失敗メッセージが表示されていること
        capture.screenShot();
        assertThat(helper.getTitle(), is("ログイン画面"));
        assertThat(page.メッセージ_エラー().getText(), is(containsString("ログインに失敗しました")));
    }

    @Test
    public void ログイン失敗_パスワード間違え() throws IOException {

        ログイン画面 page = new ログイン画面().initialize(driver);
        helper.setKeys(page.ユーザID(), "user01");
        helper.setKeys(page.パスワード(), "x");
        capture.screenShot();
        page.ログインボタン().click();

        // ログイン画面で失敗メッセージが表示されていること
        capture.screenShot();
        assertThat(helper.getTitle(), is("ログイン画面"));
        assertThat(page.メッセージ_エラー().getText(), is(containsString("ログインに失敗しました")));
    }

    @Test
    public void ログイン成功() throws IOException {

        ログイン画面 page = new ログイン画面().initialize(driver);
        helper.setKeys(page.ユーザID(), "user01");
        helper.setKeys(page.パスワード(), "pass");
        capture.screenShot();
        page.ログインボタン().click();

        // ログインに成功し、メニュー画面に遷移できていること
        capture.screenShot();
        assertThat(helper.getTitle(), is("メニュー画面"));
    }

}
