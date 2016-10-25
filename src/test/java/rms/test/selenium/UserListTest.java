package rms.test.selenium;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import org.junit.Before;
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

    @Before
    public void setup() {
        // ユーザ一覧画面表示までの初期処理
        helper.url("http://localhost:8081/login");

        {
            ログイン画面 page = new ログイン画面().initialize(driver);
            helper.sendKeys(page.ユーザID(), "user11");
            helper.sendKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            メニュー画面 page = new メニュー画面().initialize(driver);
            page.ユーザ一覧().click();
        }
    }

    @Test
    public void ユーザ一覧画面表示() throws IOException {

        // ユーザ一覧画面が表示されていること
        capture.screenShot();
        assertThat(helper.getTitle(), is("ユーザ一覧画面"));
    }

    @Test
    public void ユーザ検索_ID検索() throws IOException {

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
            page.検索条件_ユーザID().sendKeys("user01");
            page.検索ボタン().click();

            // 1件だけ表示されていること
            capture.screenShot();
            assertThat(page.ページ_結果().getText(), is("1件中 1-1件表示"));
        }
    }

    @Test
    public void ユーザ検索_改ページ() throws IOException {

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
            page.検索ボタン().click();

            // 1-5件目が表示されていること
            capture.screenShot();
            assertThat(page.ページ_結果().getText(), is(containsString("1-5件表示")));
        }

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
            page.ページ_Next().click();

            // 6-10件目が表示されていること
            capture.screenShot();
            assertThat(page.ページ_結果().getText(), is(containsString("6-10件表示")));
        }

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
            page.ページ_Prev().click();

            // 1-5件目が表示されていること
            capture.screenShot();
            assertThat(page.ページ_結果().getText(), is(containsString("1-5件表示")));
        }
    }

}
