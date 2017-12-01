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
import rms.selenium.page.MenuPage;
import rms.selenium.page.LoginPage;
import selenium.base.AbstractSeleniumTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class MenuTest extends AbstractSeleniumTest {

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
    public void test_ヘッダ_ユーザ名確認() throws IOException {

        {
            LoginPage page = new LoginPage().initialize(driver);
            helper.setKeys(page.ユーザID(), "user01");
            helper.setKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            MenuPage page = new MenuPage().initialize(driver);

            // ログインに成功し、メニュー画面のヘッダにログインユーザ名が正しく表示されていること
            capture.screenShot();
            assertThat(page.ヘッダ_ユーザ名().getText(), is("申請者０１"));
        }
    }

    @Test
    public void test_メニュー表示確認_申請者() throws IOException {

        {
            LoginPage page = new LoginPage().initialize(driver);
            helper.setKeys(page.ユーザID(), "user01");
            helper.setKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            MenuPage page = new MenuPage().initialize(driver);
            capture.screenShot();
            assertThat(helper.exists(page.ユーザ一覧()), is(false));
            assertThat(helper.exists(page.ユーザ登録()), is(false));
            assertThat(helper.exists(page.月報一覧()), is(false));
            assertThat(helper.exists(page.月報申請()), is(true));
            assertThat(helper.exists(page.月報申請状況一覧()), is(true));
            assertThat(helper.exists(page.月報承認状況一覧()), is(false));
        }
    }

    @Test
    public void test_メニュー表示確認_承認者() throws IOException {

        {
            LoginPage page = new LoginPage().initialize(driver);
            helper.setKeys(page.ユーザID(), "user06");
            helper.setKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            MenuPage page = new MenuPage().initialize(driver);
            capture.screenShot();
            assertThat(helper.exists(page.ユーザ一覧()), is(false));
            assertThat(helper.exists(page.ユーザ登録()), is(false));
            assertThat(helper.exists(page.月報一覧()), is(false));
            assertThat(helper.exists(page.月報申請()), is(false));
            assertThat(helper.exists(page.月報申請状況一覧()), is(false));
            assertThat(helper.exists(page.月報承認状況一覧()), is(true));
        }
    }

    @Test
    public void test_メニュー表示確認_管理者() throws IOException {

        {
            LoginPage page = new LoginPage().initialize(driver);
            helper.setKeys(page.ユーザID(), "user11");
            helper.setKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            MenuPage page = new MenuPage().initialize(driver);
            capture.screenShot();
            assertThat(helper.exists(page.ユーザ一覧()), is(true));
            assertThat(helper.exists(page.ユーザ登録()), is(true));
            assertThat(helper.exists(page.月報一覧()), is(true));
            assertThat(helper.exists(page.月報申請()), is(false));
            assertThat(helper.exists(page.月報申請状況一覧()), is(false));
            assertThat(helper.exists(page.月報承認状況一覧()), is(false));
        }
    }

}
