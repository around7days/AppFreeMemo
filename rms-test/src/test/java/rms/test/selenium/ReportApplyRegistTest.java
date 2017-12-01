package rms.test.selenium;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
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
import rms.test.common.mock.RmsMockFileEditor;
import rms.test.common.mock.RmsMockFileType;
import rms.test.selenium.page.メニュー画面;
import rms.test.selenium.page.ログイン画面;
import rms.test.selenium.page.月報申請画面;
import selenium.base.AbstractSeleniumTest;

@RunWith(Enclosed.class)
public class ReportApplyRegistTest {

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = SpringWebApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
    public static class 申請テストグループ extends AbstractSeleniumTest {

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
                ログイン画面 page = new ログイン画面().initialize(driver);
                helper.setKeys(page.ユーザID(), "user01");
                helper.setKeys(page.パスワード(), "pass");
                page.ログインボタン().click();
            }

            {
                メニュー画面 page = new メニュー画面().initialize(driver);
                page.月報申請().click();
            }
        }

        @Test
        public void 月報申請処理() throws IOException {

            {
                月報申請画面 page = new 月報申請画面().initialize(driver);
                capture.screenShot();

                page.申請ボタン().click();
                helper.switchToAlert().accept();
            }

            {
                メニュー画面 page = new メニュー画面().initialize(driver);
                capture.screenShot();
                assertThat(page.メッセージ_通常().getText(), is(containsString("申請が完了しました")));
            }
        }
    }
}
