package rms.test.selenium;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rms.test.selenium.page.メニュー画面;
import rms.test.selenium.page.ユーザ一覧画面;
import rms.test.selenium.page.ログイン画面;
import selenium.base.AbstractSeleniumTest;

/**
 * ユーザ一覧画面テスト
 * @author
 */
public class UserListTest extends AbstractSeleniumTest {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserListTest.class);

    @Test
    public void ユーザ一覧画面表示() throws IOException {

        /*
         * ユーザ一覧画面表示までの初期処理
         */
        initDisplay();

        // ユーザ一覧画面が表示されていること
        capture.screenShot();
        assertEquals(helper.getTitle(), "ユーザ一覧画面");
    }

    @Test
    public void ユーザ検索_ID検索() throws IOException {

        /*
         * ユーザ一覧画面表示までの初期処理
         */
        initDisplay();

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
            page.検索条件_ユーザID().sendKeys("user01");
            page.検索ボタン().click();

            // 1件だけ表示されていること
            capture.screenShot();
            assertEquals(page.ページ_結果().getText(), "1件中 1-1件表示");
        }
    }

    @Test
    public void ユーザ一覧_改ページ() throws IOException {

        /*
         * ユーザ一覧画面表示までの初期処理
         */
        initDisplay();

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
            page.検索ボタン().click();

            // 1-5件目が表示されていること
            capture.screenShot();
            assertTrue(page.ページ_結果().getText().contains("1-5件表示"));
        }

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
            page.ページ_Next().click();

            // 6-10件目が表示されていること
            capture.screenShot();
            assertTrue(page.ページ_結果().getText().contains("6-10件表示"));
        }

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
            page.ページ_Prev().click();

            // 1-5件目が表示されていること
            capture.screenShot();
            assertTrue(page.ページ_結果().getText().contains("1-5件表示"));
        }
    }

    /**
     * ユーザ一覧画面表示までの初期処理
     */
    private void initDisplay() {
        /* Webブラウザの起動 */
        helper.url("http://localhost:8081/login");

        {
            ログイン画面 page = new ログイン画面().initialize(driver);
            page.ユーザID().clear();
            page.ユーザID().sendKeys("user11");
            page.パスワード().clear();
            page.パスワード().sendKeys("pass");
            page.ログインボタン().click();
        }

        {
            メニュー画面 page = new メニュー画面().initialize(driver);
            page.ユーザ一覧().click();
        }
    }

}
