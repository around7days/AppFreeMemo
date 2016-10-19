package rms.selenium;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rms.selenium.page.LoginPage;
import rms.selenium.page.MenuPage;
import rms.selenium.page.UserListPage;
import selenium.base.AbstractSeleniumTest;

/**
 * ユーザ一覧画面テスト
 * @author
 */
public class UserListTest extends AbstractSeleniumTest {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserListTest.class);

    /**
     * ユーザ一覧画面表示<br>
     * <ol style="list-style-type: decimal-leading-zero">
     * <li></li>
     * </ol>
     * @throws IOException
     */
    @Test
    public void ユーザ一覧画面表示() throws IOException {

        /* Webブラウザの起動 */
        $url("http://localhost:8081/login");

        /*
         * ログイン画面
         */
        {
            LoginPage page = new LoginPage().initialize();
            page.sendkeysユーザID("user11");
            page.sendkeysパスワード("pass");
            page.clickログインボタン();
        }

        /*
         * メニュー画面
         */
        {
            MenuPage page = new MenuPage().initialize();
            page.clickユーザ一覧();
        }

        // ユーザ一覧画面が表示されていること
        assertEquals($getTitle(), "ユーザ一覧画面");
    }

    /**
     * ユーザ一覧画面表示<br>
     * <ol style="list-style-type: decimal-leading-zero">
     * <li></li>
     * </ol>
     * @throws IOException
     */
    @Test
    public void ユーザ検索_ID検索() throws IOException {

        /* Webブラウザの起動 */
        $url("http://localhost:8081/login");

        /*
         * ログイン画面
         */
        {
            LoginPage page = new LoginPage().initialize();
            page.sendkeysユーザID("user11");
            page.sendkeysパスワード("pass");
            page.clickログインボタン();
        }

        /*
         * メニュー画面
         */
        {
            MenuPage page = new MenuPage().initialize();
            page.clickユーザ一覧();
        }

        /*
         * ユーザ一覧画面
         */
        {
            UserListPage page = new UserListPage().initialize();
            page.sendkeys検索条件_ユーザID("user01");
            capture.screenShot();
            page.click検索ボタン();
            capture.screenShot();

            // 検索結果
            assertEquals($getTitle(), "ユーザ一覧画面");
        }
    }
}
