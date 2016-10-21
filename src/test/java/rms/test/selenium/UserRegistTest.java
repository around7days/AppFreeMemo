package rms.test.selenium;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.support.ui.Select;
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
        assertEquals($getTitle(), "ユーザ登録画面");
    }

    @Test
    public void ユーザ登録画面表示_更新() throws IOException {

        /*
         * ユーザ登録画面（更新）表示までの初期処理
         */
        initDisplayUpdate();

        // ユーザ登録画面が表示されていること
        capture.screenShot();
        assertEquals($getTitle(), "ユーザ登録画面");

        // 表示されている値が正しいか確認
        ユーザ登録画面 page = new ユーザ登録画面().initialize();
        assertEquals(page.ユーザID().getText(), "user01");
        assertEquals(page.ユーザ名().getAttribute("value"), "申請者０１");
        assertEquals(page.パスワード().getAttribute("value"), "pass");
        assertEquals(page.メールアドレス().getAttribute("value"), "xxx@xxx.xx");
        assertEquals(new Select(page.部署ID()).getFirstSelectedOption().getText(), "1SOL");
        assertEquals(new Select(page.承認者ID1()).getFirstSelectedOption().getText(), "承認者０１");
        assertEquals(new Select(page.承認者ID2()).getFirstSelectedOption().getText(), "承認者０２");
        assertEquals(new Select(page.承認者ID3()).getFirstSelectedOption().getText(), "承認者０３");
        assertEquals(page.役割_申請者().isSelected(), true);
        assertEquals(page.役割_承認者().isSelected(), false);
        assertEquals(page.役割_管理者().isSelected(), false);
    }

    /**
     * ユーザ登録画面（新規）までの初期処理
     */
    private void initDisplayInsert() {
        /* Webブラウザの起動 */
        $url("http://localhost:8081/login");

        {
            ログイン画面 page = new ログイン画面().initialize();
            page.ユーザID().clear();
            page.ユーザID().sendKeys("user11");
            page.パスワード().clear();
            page.パスワード().sendKeys("pass");
            page.ログインボタン().click();
        }

        {
            メニュー画面 page = new メニュー画面().initialize();
            page.ユーザ登録().click();
        }
    }

    /**
     * ユーザ登録画面（更新）までの初期処理
     */
    private void initDisplayUpdate() {
        /* Webブラウザの起動 */
        $url("http://localhost:8081/login");

        {
            ログイン画面 page = new ログイン画面().initialize();
            page.ユーザID().clear();
            page.ユーザID().sendKeys("user11");
            page.パスワード().clear();
            page.パスワード().sendKeys("pass");
            page.ログインボタン().click();
        }

        {
            メニュー画面 page = new メニュー画面().initialize();
            page.ユーザ一覧().click();
        }

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize();
            page.検索条件_ユーザID().sendKeys("user01");
            page.検索ボタン().click();
        }

        {
            ユーザ一覧画面 page = new ユーザ一覧画面().initialize();
            page.検索結果_選択ボタン().get(0).click();
        }
    }

}
