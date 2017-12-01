package rms.selenium.page;

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
public class LoginPage extends rms.selenium.page.CommonPage {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

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
    public LoginPage initialize(WebDriver driver) {
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
