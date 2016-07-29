//package rms.selenium;
//
//import java.io.IOException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.junit.Test;
//
//import selenium.com.AbstractSeleniumTest;
//import selenium.sample.page.ログイン画面;
//
///**
// * ログイン画面テスト
// * @author
// */
//public class LoginTest extends AbstractSeleniumTest {
//
//    /** ロガー */
//    @SuppressWarnings("unused")
//    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
//
//    /**
//     * ログイン画面表示
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    @Test
//    public void ログイン画面表示() throws IOException, InterruptedException {
//        /*
//         * キャプチャ設定
//         */
//        capture.setPrefix("ログイン画面");
//
//        /*
//         * ログイン画面 ------------------------------------------------
//         */
//        /* Webブラウザの起動 */
//        $url("http://localhost:8081/login");
//        {
//            ログイン画面 page = new ログイン画面().initialize();
//
//            capture.screenShot();
//
//            page.sendkeysユーザー名("kksp");
//            page.sendkeysパスワード("k");
//
//            capture.screenShot();
//
//            page.clickログインボタン();
//        }
//    }
//}
