//package rms.selenium;
//
//import java.io.IOException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.junit.Test;
//
///**
// * テスト１
// * @author 7days
// */
//public class Test1 extends AbstractSeleniumTest {
//
//    /** ロガー */
//    @SuppressWarnings("unused")
//    private static final Logger logger = LoggerFactory.getLogger(Test1.class);
//
//    /**
//     * マニフェスト登録～２次マニフェスト登録～最終処分報告
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    @Test
//    public void マニフェスト登録_２次マニフェスト_登録最終処分報告() throws IOException, InterruptedException {
//        No1_1次マニフェスト登録();
//    }
//
//    /**
//     * No1_1次マニフェスト登録
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    private void No1_1次マニフェスト登録() throws IOException, InterruptedException {
//
//        /*
//         * キャプチャ設定
//         */
//        capture.setPrefix("No1_テスト");
//
//        /*
//         * ログイン画面 ------------------------------------------------
//         */
//        $url("http://pisetmdbdv01.mew.co.jp/et_pt/ActionServlet");
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
//
//        /*
//         * メニュー画面（Frame） ------------------------------------------------
//         */
//        {
//            メニュー画面 page = new メニュー画面().initialize();
//
//            capture.screenShot();
//
//            // ヘッダフレームに移動
//            page.frameChangeヘッダフレーム();
//        }
//
//        /*
//         * メニュー_ヘッダ画面 ------------------------------------------------
//         */
//        {
//            メニュー_ヘッダ画面 page = new メニュー_ヘッダ画面().initialize();
//
//            page.clickグループヘルプボタン();
//        }
//
//        /*
//         * グループヘルプ画面 ------------------------------------------------
//         */
//        {
//            // 子画面に切り替える
//            $switchToWindowHandle();
//
//            ヘルプ_グループ検索一覧画面 page = new ヘルプ_グループ検索一覧画面().initialize();
//
//            page.click検索条件_出力範囲_グループ配下含む();
//            page.sendkeys検索条件_事業場コード("kabe0000000");
//            page.click検索ボタン();
//
//            page.click一覧_業者名アンカー(2);
//        }
//
//        /*
//         * メニュー画面（Frame） ------------------------------------------------
//         */
//        {
//            // ベース画面に切り替える
//            $switchToBaseWindowHandle();
//
//            メニュー画面 page = new メニュー画面().initialize();
//
//            // ヘッダフレームに移動
//            page.frameChangeヘッダフレーム();
//        }
//
//        /*
//         * メニュー_ヘッダ画面 ------------------------------------------------
//         */
//        {
//            メニュー_ヘッダ画面 page = new メニュー_ヘッダ画面().initialize();
//
//            page.click決定ボタン();
//        }
//
//        /*
//         * メニュー画面（Frame） ------------------------------------------------
//         */
//        {
//            // アラートにOK
//            $switchToAlert().accept();
//
//            メニュー画面 page = new メニュー画面().initialize();
//
//            page.frameChangeメインフレーム();
//        }
//
//        /*
//         * マニフェスト予約・登録 ------------------------------------------------
//         */
//        {
//            // アラートにOK
//            $switchToAlert().accept();
//
//            メニュー画面 page = new メニュー画面().initialize();
//
//            page.frameChangeメインフレーム();
//        }
//
//    }
//}
