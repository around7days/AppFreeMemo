package rms.test.selenium;

import static org.junit.Assert.*;

import java.io.IOException;

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

    @Test
    public void ログイン画面表示() throws IOException {

        /*
         * ログイン画面表示までの初期処理
         */
        initDisplay();

        capture.screenShot();
        assertEquals(helper.getTitle(), "ログイン画面");
    }

    @Test
    public void ログイン失敗_ID間違え() throws IOException {

        /*
         * ログイン画面表示までの初期処理
         */
        initDisplay();

        ログイン画面 page = new ログイン画面().initialize(driver);
        page.ユーザID().clear();
        page.ユーザID().sendKeys("xxxx01");
        page.パスワード().clear();
        page.パスワード().sendKeys("pass");
        capture.screenShot();
        page.ログインボタン().click();

        // ログイン画面で失敗メッセージが表示されていること
        capture.screenShot();
        assertEquals(helper.getTitle(), "ログイン画面");
        assertTrue(page.メッセージ_エラー().getText().contains("ログインに失敗しました"));
    }

    @Test
    public void ログイン失敗_パスワード間違え() throws IOException {

        /*
         * ログイン画面表示までの初期処理
         */
        initDisplay();

        ログイン画面 page = new ログイン画面().initialize(driver);
        page.ユーザID().clear();
        page.ユーザID().sendKeys("user01");
        page.パスワード().clear();
        page.パスワード().sendKeys("x");
        capture.screenShot();
        page.ログインボタン().click();

        // ログイン画面で失敗メッセージが表示されていること
        capture.screenShot();
        assertEquals(helper.getTitle(), "ログイン画面");
        assertTrue(page.メッセージ_エラー().getText().contains("ログインに失敗しました"));
    }

    @Test
    public void ログイン成功() throws IOException {

        /*
         * ログイン画面表示までの初期処理
         */
        initDisplay();

        ログイン画面 page = new ログイン画面().initialize(driver);
        page.ユーザID().clear();
        page.ユーザID().sendKeys("user01");
        page.パスワード().clear();
        page.パスワード().sendKeys("pass");
        capture.screenShot();
        page.ログインボタン().click();

        // ログインに成功し、メニュー画面に遷移できていること
        capture.screenShot();

        assertEquals(helper.getTitle(), "メニュー画面");
    }

    /**
     * ログイン画面表示までの初期処理
     */
    private void initDisplay() {
        /* Webブラウザの起動 */
        helper.url("http://localhost:8081/login");
    }
}
