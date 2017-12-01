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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import mockit.Mock;
import mockit.MockUp;
import rms.SpringWebApplication;
import rms.common.interceptor.ControllerAdviceCustom;
import rms.selenium.page.LoginPage;
import rms.selenium.page.MenuPage;
import rms.selenium.page.ReportApplyPage;
import rms.testutil.mock.RmsMockFileEditor;
import rms.testutil.mock.RmsMockFileType;
import selenium.base.AbstractSeleniumTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReportApplyRegistTest extends AbstractSeleniumTest {

    @LocalServerPort
    private int port;

    @Value("${server.context-path}")
    private String serverContextPath;

    @Before
    public void setup() {

        // モック定義 --------------------------------------------------------------------------------------------------
        new MockUp<ControllerAdviceCustom>() {
            @Mock
            @InitBinder
            public void initBinderMock(WebDataBinder dataBinder) {
                dataBinder.registerCustomEditor(MultipartFile.class, new RmsMockFileEditor(RmsMockFileType.XLSX));
            }
        };
        // ---------------------------------------------------------------------------------------------------------

        // 月報登録画面（新規）までの初期処理
        helper.url("http://localhost:" + port + serverContextPath + "/login");

        {
            LoginPage page = new LoginPage().initialize(driver);
            helper.setKeys(page.ユーザID(), "user01");
            helper.setKeys(page.パスワード(), "pass");
            page.ログインボタン().click();
        }

        {
            MenuPage page = new MenuPage().initialize(driver);
            page.月報申請().click();
        }
    }

    @Test
    public void test_月報申請処理() throws IOException {

        {
            ReportApplyPage page = new ReportApplyPage().initialize(driver);
            capture.screenShot();

            page.申請ボタン().click();
            helper.switchToAlert().accept();
        }

        {
            MenuPage page = new MenuPage().initialize(driver);
            capture.screenShot();
            assertThat(page.メッセージ_通常().getText(), is(containsString("申請が完了しました")));
        }
    }
}
