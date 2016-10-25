package rms.test.selenium;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rms.test.selenium.page.メニュー画面;
import rms.test.selenium.page.ユーザ一覧画面;
import rms.test.selenium.page.ユーザ登録画面;
import rms.test.selenium.page.ログイン画面;
import selenium.base.AbstractSeleniumTest;

/**
 * ユーザ登録画面テスト
 * @author
 */
@RunWith(Enclosed.class)
public class UserRegistTest extends AbstractSeleniumTest {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserRegistTest.class);

    public static class 新規登録テストグループ {

        @Before
        public void setup() {
            // ユーザ登録画面（新規）までの初期処理
            helper.url("http://localhost:8081/login");

            {
                ログイン画面 page = new ログイン画面().initialize(driver);
                helper.sendKeys(page.ユーザID(), "user11");
                helper.sendKeys(page.パスワード(), "pass");
                page.ログインボタン().click();
            }

            {
                メニュー画面 page = new メニュー画面().initialize(driver);
                page.ユーザ登録().click();
            }
        }

        @Test
        public void ユーザ登録画面表示() throws IOException {
            // ユーザ登録画面が表示されていること
            capture.screenShot();
            assertThat(helper.getTitle(), is("ユーザ登録画面"));
        }

        @Test
        public void ユーザ登録処理() throws IOException {

            {
                ユーザ登録画面 page = new ユーザ登録画面().initialize(driver);
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
                helper.$switchToAlert().accept();
            }

            {
                ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
                capture.screenShot();
                assertThat(helper.getTitle(), is(containsString("ユーザ一覧画面")));
                assertThat(page.メッセージ_通常().getText(), is(containsString("登録が完了しました")));
            }
        }
    }

    public static class 更新テストグループ {

        @Before
        public void setup() {
            // ユーザ登録画面（更新）までの初期処理
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

            {
                ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
                page.検索条件_ユーザID().sendKeys("user01");
                page.検索ボタン().click();
            }

            {
                ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
                page.検索結果_選択ボタン().get(0).click();
            }
        }

        @Test
        public void ユーザ更新画面表示() throws IOException {

            // ユーザ登録画面が表示されていること
            capture.screenShot();
            assertThat(helper.getTitle(), is("ユーザ登録画面"));

            // 表示されている値が正しいか確認
            ユーザ登録画面 page = new ユーザ登録画面().initialize(driver);
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
        public void ユーザ更新処理_値変更なし() throws IOException {

            {
                ユーザ登録画面 page = new ユーザ登録画面().initialize(driver);
                capture.screenShot();
                page.更新ボタン().click();
                helper.$switchToAlert().accept();
            }

            {
                ユーザ一覧画面 page = new ユーザ一覧画面().initialize(driver);
                capture.screenShot();
                assertThat(helper.getTitle(), is(containsString("ユーザ一覧画面")));
                assertThat(page.メッセージ_通常().getText(), is(containsString("更新が完了しました")));
            }
        }
    }
}
