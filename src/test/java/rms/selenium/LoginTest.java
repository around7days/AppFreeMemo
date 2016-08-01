package rms.selenium;

import static org.junit.Assert.*;

import java.io.IOException;

import rms.selenium.com.page.Login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.base.AbstractSeleniumTest;

/**
 * ログイン画面テスト
 * @author
 */
public class LoginTest extends AbstractSeleniumTest {

    /** ロガー */
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    /**
     * ログイン画面表示<br>
     * <ol style="list-style-type: decimal-leading-zero">
     * <li>ログイン画面表示</li>
     * </ol>
     * @throws IOException
     */
    @Test
    public void ログイン画面表示() throws IOException {

        /*
         * ログイン画面表示
         */
        {
            /* Webブラウザの起動 */
            $url("http://localhost:8081/login");

            capture.screenShot();

            logger.debug("タイトル -> {}", $getTitle());
            assertEquals($getTitle(), "ログイン画面");
        }
    }

    /**
     * ログイン失敗(ID間違え)<br>
     * <ol style="list-style-type: decimal-leading-zero">
     * <li>ログイン失敗(ID間違え)</li>
     * </ol>
     * @throws IOException
     */
    @Test
    public void ログイン失敗_ID間違え() throws IOException {

        /* Webブラウザの起動 */
        $url("http://localhost:8081/login");

        /*
         * ログイン失敗(ID間違え)
         */
        {
            Login page = new Login().initialize();

            page.sendkeysユーザID("xxxx01");
            page.sendkeysパスワード("pass");

            capture.screenShot();

            page.clickログインボタン();

            capture.screenShot();

            logger.debug("タイトル -> {}", $getTitle());
            assertEquals($getTitle(), "ログイン画面");

            WebElement element = $(By.id("alert-dangerx"));
            assertTrue(element.getText().contains("ログインに失敗しました"));
        }
    }

    /**
     * ログイン失敗(パスワード間違え)<br>
     * <ol style="list-style-type: decimal-leading-zero">
     * <li>ログイン失敗(パスワード間違え)</li>
     * </ol>
     * @throws IOException
     */
    @Test
    public void ログイン失敗_パスワード間違え() throws IOException {

        /* Webブラウザの起動 */
        $url("http://localhost:8081/login");

        /*
         * ログイン失敗(パスワード間違え)
         */
        {
            Login page = new Login().initialize();

            page.sendkeysユーザID("user01");
            page.sendkeysパスワード("x");

            capture.screenShot();

            page.clickログインボタン();

            capture.screenShot();

            logger.debug("タイトル -> {}", $getTitle());
            assertEquals($getTitle(), "ログイン画面");

            WebElement element = $(By.id("alert-danger"));
            assertTrue(element.getText().contains("ログインに失敗しました"));
        }

    }

    /**
     * ログイン成功<br>
     * <ol style="list-style-type: decimal-leading-zero">
     * <li>ログイン成功</li>
     * </ol>
     * @throws IOException
     */
    @Test
    public void ログイン成功() throws IOException {

        /* Webブラウザの起動 */
        $url("http://localhost:8081/login");

        /*
         * ログイン成功
         */
        {
            Login page = new Login().initialize();

            page.sendkeysユーザID("user01");
            page.sendkeysパスワード("pass");

            capture.screenShot();

            page.clickログインボタン();

            capture.screenShot();

            logger.debug("タイトル -> {}", $getTitle());
            assertEquals($getTitle(), "メニュー画面");
        }
    }
}
