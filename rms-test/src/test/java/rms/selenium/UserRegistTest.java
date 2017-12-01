package rms.selenium;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
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
import rms.selenium.page.UserRegistPage;
import selenium.base.AbstractSeleniumTest;

@RunWith(Enclosed.class)
public class UserRegistTest {

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = SpringWebApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
    public static class 新規登録テストグループ extends AbstractSeleniumTest {

        @LocalServerPort
        private int port;

        @Value("${server.context-path}")
        private String serverContextPath;

        @Before
        public void setup() {
            // ユーザ登録画面（新規）までの初期処理
            helper.url("http://localhost:" + port + serverContextPath + "/login");

            {
                LoginPage page = new LoginPage().initialize(driver);
                helper.setKeys(page.ユーザID(), "user11");
                helper.setKeys(page.パスワード(), "pass");
                page.ログインボタン().click();
            }

            {
                MenuPage page = new MenuPage().initialize(driver);
                page.ユーザ登録().click();
            }
        }

        @Test
        public void test_ユーザ登録画面表示() throws IOException {
            // ユーザ登録画面が表示されていること
            capture.screenShot();
            assertThat(helper.getTitle(), is("ユーザ登録画面"));
        }

        @Test
        public void test_ユーザ登録処理() throws IOException {

            {
                UserRegistPage page = new UserRegistPage().initialize(driver);
                String userId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmssSSS"));
                page.ユーザID().sendKeys(userId);
                page.ユーザ名().sendKeys("テストユーザ" + userId);
                page.パスワード().sendKeys("pass");
                page.メールアドレス().sendKeys("abc@def.ghi");
                helper.selectByText(page.部署セレクト(), "2SOL");
                helper.selectByText(page.承認者１セレクト(), "承認者０１");
                helper.selectByText(page.承認者２セレクト(), "承認者０２");
                helper.selectByText(page.承認者３セレクト(), "承認者０３");
                page.役割_申請者チェック().click();

                capture.screenShot();
                page.登録ボタン().click();
                helper.switchToAlert().accept();
            }

            {
                UserListPage page = new UserListPage().initialize(driver);
                capture.screenShot();
                assertThat(helper.getTitle(), is(containsString("ユーザ一覧画面")));
                assertThat(page.メッセージ_通常().getText(), is(containsString("登録が完了しました")));
            }
        }

        @Test
        public void test_ユーザ登録_重複エラー() throws IOException {

            {
                UserRegistPage page = new UserRegistPage().initialize(driver);
                page.ユーザID().sendKeys("user11");
                page.ユーザ名().sendKeys("テストユーザ");
                page.パスワード().sendKeys("pass");

                capture.screenShot();
                page.登録ボタン().click();
                helper.switchToAlert().accept();
            }

            {
                UserRegistPage page = new UserRegistPage().initialize(driver);
                capture.screenShot();
                assertThat(helper.getTitle(), is(containsString("ユーザ登録画面")));
                assertThat(page.メッセージ_エラー().getText(), is(containsString("ユーザIDが重複しています")));
            }
        }

    }

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = SpringWebApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
    public static class 更新テストグループ extends AbstractSeleniumTest {

        @LocalServerPort
        private int port;

        @Value("${server.context-path}")
        private String serverContextPath;

        @Before
        public void setup() {
            // ユーザ登録画面（更新）までの初期処理
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

            {
                UserListPage page = new UserListPage().initialize(driver);
                page.検索条件_ユーザID().sendKeys("user01");
                page.検索ボタン().click();
            }

            {
                UserListPage page = new UserListPage().initialize(driver);
                page.検索結果_選択ボタン().get(0).click();
            }
        }

        @Test
        public void test_ユーザ更新画面表示() throws IOException {

            // ユーザ登録画面が表示されていること
            capture.screenShot();
            assertThat(helper.getTitle(), is("ユーザ登録画面"));

            // 表示されている値が正しいか確認
            UserRegistPage page = new UserRegistPage().initialize(driver);
            assertThat(page.ユーザID().getText(), is("user01"));
            assertThat(helper.getValue(page.ユーザ名()), is("申請者０１"));
            assertThat(helper.getValue(page.パスワード()), is("pass"));
            assertThat(helper.getValue(page.メールアドレス()), is("xxx@xxx.xx"));
            assertThat(helper.getSelectedText(page.部署セレクト()), is("1SOL"));
            assertThat(helper.getSelectedText(page.承認者１セレクト()), is("承認者０１"));
            assertThat(helper.getSelectedText(page.承認者２セレクト()), is("承認者０２"));
            assertThat(helper.getSelectedText(page.承認者３セレクト()), is("承認者０３"));
            assertThat(page.役割_申請者チェック().isSelected(), is(true));
            assertThat(page.役割_承認者チェック().isSelected(), is(false));
            assertThat(page.役割_管理者チェック().isSelected(), is(false));
        }

        @Test
        public void test_ユーザ更新処理_値変更なし() throws IOException {

            {
                UserRegistPage page = new UserRegistPage().initialize(driver);
                capture.screenShot();
                page.更新ボタン().click();
                helper.switchToAlert().accept();
            }

            {
                UserListPage page = new UserListPage().initialize(driver);
                capture.screenShot();
                assertThat(helper.getTitle(), is(containsString("ユーザ一覧画面")));
                assertThat(page.メッセージ_通常().getText(), is(containsString("更新が完了しました")));
            }
        }
    }
}
