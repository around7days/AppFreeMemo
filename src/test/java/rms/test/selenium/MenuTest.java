package rms.test.selenium;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import rms.test.selenium.page.メニュー画面;
import rms.test.selenium.page.ログイン画面;
import selenium.base.AbstractSeleniumTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MenuTest extends AbstractSeleniumTest {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        // ログイン画面表示までの初期処理
        helper.url("http://localhost:" + port + "/login");
    }

    @Test
    public void ヘッダ_ユーザ名確認() throws IOException {

        {
            ログイン画面 page = new ログイン画面().initialize(driver);
            helper.sendKeys(page.ユーザID(), "user01");
            helper.sendKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            メニュー画面 page = new メニュー画面().initialize(driver);

            // ログインに成功し、メニュー画面のヘッダにログインユーザ名が正しく表示されていること
            capture.screenShot();
            assertThat(page.ヘッダ_ユーザ名().getText(), is("申請者０１"));
        }
    }

    @Test
    public void メニュー表示確認_申請者() throws IOException {

        {
            ログイン画面 page = new ログイン画面().initialize(driver);
            helper.sendKeys(page.ユーザID(), "user01");
            helper.sendKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            メニュー画面 page = new メニュー画面().initialize(driver);
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
    public void メニュー表示確認_承認者() throws IOException {

        {
            ログイン画面 page = new ログイン画面().initialize(driver);
            helper.sendKeys(page.ユーザID(), "user06");
            helper.sendKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            メニュー画面 page = new メニュー画面().initialize(driver);
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
    public void メニュー表示確認_管理者() throws IOException {

        {
            ログイン画面 page = new ログイン画面().initialize(driver);
            helper.sendKeys(page.ユーザID(), "user11");
            helper.sendKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            メニュー画面 page = new メニュー画面().initialize(driver);
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
