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
import rms.test.selenium.page.ユーザ一覧画面;
import rms.test.selenium.page.ログイン画面;
import selenium.base.AbstractSeleniumTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserListTest extends AbstractSeleniumTest {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        // ユーザ一覧画面表示までの初期処理
        helper.url("http://localhost:" + port + "/login");

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
