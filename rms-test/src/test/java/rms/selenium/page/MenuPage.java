package rms.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * メニュー画面クラス
 */
public class MenuPage extends rms.selenium.page.CommonPage {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MenuPage.class);

    /** ユーザ一覧 */
    @FindBy(name = "M001")
    @CacheLookup
    private WebElement ユーザ一覧;

    /** ユーザ登録 */
    @FindBy(name = "M002")
    @CacheLookup
    private WebElement ユーザ登録;

    /** 月報一覧 */
    @FindBy(name = "T001")
    @CacheLookup
    private WebElement 月報一覧;

    /** 月報申請 */
    @FindBy(name = "T002")
    @CacheLookup
    private WebElement 月報申請;

    /** 月報申請状況一覧 */
    @FindBy(name = "T003")
    @CacheLookup
    private WebElement 月報申請状況一覧;

    /** 月報承認状況一覧 */
    @FindBy(name = "T006")
    @CacheLookup
    private WebElement 月報承認状況一覧;

    /**
     * PageFactoryを使用してWebElementをマッピングする
     * @param driver
     * @return メニュー画面
     */
    public MenuPage initialize(WebDriver driver) {
        return PageFactory.initElements(driver, this.getClass());
    }

    public WebElement ユーザ一覧() {
        return this.ユーザ一覧;
    }

    public WebElement ユーザ登録() {
        return this.ユーザ登録;
    }

    public WebElement 月報一覧() {
        return this.月報一覧;
    }

    public WebElement 月報申請() {
        return this.月報申請;
    }

    public WebElement 月報申請状況一覧() {
        return this.月報申請状況一覧;
    }

    public WebElement 月報承認状況一覧() {
        return this.月報承認状況一覧;
    }

}
