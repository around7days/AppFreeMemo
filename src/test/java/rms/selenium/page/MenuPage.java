package rms.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MenuPageクラス
 */
public class MenuPage extends rms.selenium.page.AbstractCommonPage {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MenuPage.class);

    /* 項目変数宣言 ------------------------------------------------------------------------------------------------- */
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

    /* 共通メソッド宣言 --------------------------------------------------------------------------------------------- */
    /**
     * PageFactoryを使用してWebElementをマッピングする
     * @return Page
     */
    public MenuPage initialize() {
        return PageFactory.initElements(driver, this.getClass());
    }

    /* IE操作メソッド ----------------------------------------------------------------------------------------------- */

    /**
     * ユーザ一覧をクリックします。
     */
    public void clickユーザ一覧() {
        this.ユーザ一覧.click();
    }

    /**
     * ユーザ登録をクリックします。
     */
    public void clickユーザ登録() {
        this.ユーザ登録.click();
    }

    /**
     * 月報一覧をクリックします。
     */
    public void click月報一覧() {
        this.月報一覧.click();
    }

    /**
     * 月報申請をクリックします。
     */
    public void click月報申請() {
        this.月報申請.click();
    }

    /**
     * 月報申請状況一覧をクリックします。
     */
    public void click月報申請状況一覧() {
        this.月報申請状況一覧.click();
    }

    /**
     * 月報承認状況一覧をクリックします。
     */
    public void click月報承認状況一覧() {
        this.月報承認状況一覧.click();
    }

}
