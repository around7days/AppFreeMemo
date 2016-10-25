package rms.test.selenium;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rms.test.selenium.page.ログイン画面;
import selenium.base.AbstractSeleniumTest;

/**
 * ログイン画面テスト
 * @author
 */
public class LoginTest extends AbstractSeleniumTest {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Before
    public void setup() {
        // ログイン画面表示までの初期処理
        helper.url("http://localhost:8081/login");
    }

    @Test
    public void ログイン画面表示() throws IOException {
        capture.screenShot();
        assertThat(helper.getTitle(), is("ログイン画面"));
    }

    @Test
    public void ログイン失敗_ID間違え() throws IOException {

        ログイン画面 page = new ログイン画面().initialize(driver);
        helper.sendKeys(page.ユーザID(), "xxxx01");
        helper.sendKeys(page.パスワード(), "pass");
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
        helper.sendKeys(page.ユーザID(), "user01");
        helper.sendKeys(page.パスワード(), "x");
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
        helper.sendKeys(page.ユーザID(), "user01");
        helper.sendKeys(page.パスワード(), "pass");
        capture.screenShot();
        page.ログインボタン().click();

        // ログインに成功し、メニュー画面に遷移できていること
        capture.screenShot();
        assertThat(helper.getTitle(), is("メニュー画面"));
    }

}
