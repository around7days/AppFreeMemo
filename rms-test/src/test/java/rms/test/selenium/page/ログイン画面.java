package rms.test.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ログイン画面クラス
 */
public class ログイン画面 extends rms.test.selenium.page.共通部品 {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ログイン画面.class);

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

    /**
     * PageFactoryを使用してWebElementをマッピングする
     * @param driver
     * @return ログイン画面
     */
    public ログイン画面 initialize(WebDriver driver) {
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

}
