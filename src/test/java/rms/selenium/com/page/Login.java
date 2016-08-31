//package rms.selenium.com.page;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.CacheLookup;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
///**
// * Loginクラス
// */
//public class Login extends selenium.base.AbstractSeleniumPage {
//
//    /** ロガー */
//    @SuppressWarnings("unused")
//    private static final Logger logger = LoggerFactory.getLogger(Login.class);
//
//    /* 項目変数宣言 ------------------------------------------------------------------------------------------------- */
//    /** ユーザID */
//    @FindBy(id = "userId")
//    @CacheLookup
//    private WebElement ユーザID;
//
//    /** パスワード */
//    @FindBy(id = "password")
//    @CacheLookup
//    private WebElement パスワード;
//
//    /** ログインボタン */
//    @FindBy(id = "login")
//    @CacheLookup
//    private WebElement ログインボタン;
//
//    /* 共通メソッド宣言 ------------------------------------------------------------------------------------------------- */
//    /**
//     * PageFactoryを使用してWebElementをマッピングする
//     * @return Page
//     */
//    public Login initialize() {
//        return PageFactory.initElements(driver, this.getClass());
//    }
//
//    /* IE操作メソッド ----------------------------------------------------------------------------------------------- */
//    /**
//     * ユーザIDに値を入力します。
//     * @param sendKeys
//     */
//    public void sendkeysユーザID(String sendKeys) {
//        this.ユーザID.clear();
//        this.ユーザID.sendKeys(sendKeys);
//    }
//
//    /**
//     * パスワードに値を入力します。
//     * @param sendKeys
//     */
//    public void sendkeysパスワード(String sendKeys) {
//        this.パスワード.clear();
//        this.パスワード.sendKeys(sendKeys);
//    }
//
//    /**
//     * ログインボタンをクリックします。
//     */
//    public void clickログインボタン() {
//        this.ログインボタン.click();
//    }
//
//}
