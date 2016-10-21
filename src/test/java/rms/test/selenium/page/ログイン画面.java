package rms.test.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ログイン画面クラス
 */
public class ログイン画面 extends rms.test.selenium.page.CommonParts {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ログイン画面.class);

    /* 項目変数宣言 ------------------------------------------------------------------------------------------------- */
    /** ユーザID */
    @FindBy(id = "userId")
    @CacheLookup
    private WebElement ユーザID;

    /** パスワード */
    @FindBy(id = "password")
    @CacheLookup
    private WebElement パスワード;

    /** ログインボタン */
    @FindBy(id = "login")
    @CacheLookup
    private WebElement ログインボタン;

    /* 共通メソッド宣言 --------------------------------------------------------------------------------------------- */
    /**
     * PageFactoryを使用してWebElementをマッピングする
     * @return Page
     */
    public ログイン画面 initialize() {
        return PageFactory.initElements(driver, this.getClass());
    }

    public WebElement ユーザID() {
        return this.ユーザID;
    }

    public WebElement パスワード() {
        return this.パスワード;
    }

    public WebElement ログインボタン() {
        return this.ログインボタン;
    }

    /* IE操作メソッド ----------------------------------------------------------------------------------------------- */

}
