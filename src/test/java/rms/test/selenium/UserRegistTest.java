package rms.test.selenium;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
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
public class UserRegistTest extends AbstractSeleniumTest {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserRegistTest.class);

    @Test
    public void ユーザ登録画面表示_新規() throws IOException {

        /*
         * ユーザ登録画面（新規）表示までの初期処理
         */
        initDisplayInsert();

        // ユーザ登録画面が表示されていること
        capture.screenShot();
        assertEquals(helper.getTitle(), "ユーザ登録画面");
    }

    @Test
    public void ユーザ登録画面表示_更新() throws IOException {

        /*
         * ユーザ登録画面（更新）表示までの初期処理
         */
        initDisplayUpdate();

        // ユーザ登録画面が表示されていること
        capture.screenShot();
        assertEquals(helper.getTitle(), "ユーザ登録画面");

        // 表示されている値が正しいか確認
        ユーザ登録画面 page = new ユーザ登録画面().initialize(driver);
        assertEquals(page.ユーザID().getText(), "user01");
        assertEquals(helper.getValue(page.ユーザ名()), "申請者０１");
        assertEquals(helper.getValue(page.パスワード()), "pass");
        assertEquals(helper.getValue(page.メールアドレス()), "xxx@xxx.xx");
        assertEquals(helper.getSelectedText(page.部署セレクト()), "1SOL");
        assertEquals(helper.getSelectedText(page.承認者１セレクト()), "承認者０１");
        assertEquals(helper.getSelectedText(page.承認者２セレクト()), "承認者０２");
        assertEquals(helper.getSelectedText(page.承認者３セレクト()), "承認者０３");
        assertEquals(page.役割_申請者チェック().isSelected(), true);
        assertEquals(page.役割_承認者チェック().isSelected(), false);
        assertEquals(page.役割_管理者チェック().isSelected(), false);
    }

    /**
     * ユーザ登録画面（新規）までの初期処理
     */
    private void initDisplayInsert() {
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
            page.ユーザ登録().click();
        }
    }

    /**
     * ユーザ登録画面（更新）までの初期処理
     */
    private void initDisplayUpdate() {
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

}
