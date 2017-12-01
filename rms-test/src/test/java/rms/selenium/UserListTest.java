package rms.selenium;

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

import rms.SpringWebApplication;
import rms.selenium.page.LoginPage;
import rms.selenium.page.MenuPage;
import rms.selenium.page.UserListPage;
import selenium.base.AbstractSeleniumTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserListTest extends AbstractSeleniumTest {

    @LocalServerPort
    private int port;

    @Value("${server.context-path}")
    private String serverContextPath;

    @Before
    public void setup() {
        // ユーザ一覧画面表示までの初期処理
        helper.url("http://localhost:" + port + serverContextPath + "/login");

        {
            LoginPage page = new LoginPage().initialize(driver);
            helper.setKeys(page.ユーザID(), "user11");
            helper.setKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            MenuPage page = new MenuPage().initialize(driver);
            page.ユーザ一覧().click();
        }
    }

    @Test
    public void test_ユーザ一覧画面表示() throws IOException {

        // ユーザ一覧画面が表示されていること
        capture.screenShot();
        assertThat(helper.getTitle(), is("ユーザ一覧画面"));
    }

    @Test
    public void test_ユーザ検索_ID検索() throws IOException {

        {
            UserListPage page = new UserListPage().initialize(driver);
            page.検索条件_ユーザID().sendKeys("user01");
            page.検索ボタン().click();

            // 1件だけ表示されていること
            capture.screenShot();
            assertThat(page.ページ_結果().getText(), is("1件中 1-1件表示"));
        }
    }

    @Test
    public void test_ユーザ検索_改ページ() throws IOException {

        {
            UserListPage page = new UserListPage().initialize(driver);
            page.検索ボタン().click();

            // 1-5件目が表示されていること
            capture.screenShot();
            assertThat(page.ページ_結果().getText(), is(containsString("1-5件表示")));
        }

        {
            UserListPage page = new UserListPage().initialize(driver);
            page.ページ_Next().click();

            // 6-10件目が表示されていること
            capture.screenShot();
            assertThat(page.ページ_結果().getText(), is(containsString("6-10件表示")));
        }

        {
            UserListPage page = new UserListPage().initialize(driver);
            page.ページ_Prev().click();

            // 1-5件目が表示されていること
            capture.screenShot();
            assertThat(page.ページ_結果().getText(), is(containsString("1-5件表示")));
        }
    }

}
