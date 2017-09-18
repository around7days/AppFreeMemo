package rms.test.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 月報申請画面クラス
 */
public class 月報申請画面 extends rms.test.selenium.page.共通部品 {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(月報申請画面.class);

    /** 年月 */
    @FindBy(id = "targetYm")
    @CacheLookup
    private WebElement 年月;

    /** 月報ファイル */
    @FindBy(xpath = "//input[@type='file']")
    @CacheLookup
    private WebElement 月報ファイル;

    /** フェイクファイルテキスト */
    @FindBy(id = "fakeFileText")
    @CacheLookup
    private WebElement フェイクファイルテキスト;

    /** ファイル選択ボタン */
    @FindBy(partialLinkText = "ファイル選択")
    @CacheLookup
    private WebElement ファイル選択ボタン;

    /** 戻るボタン */
    @FindBy(id = "back")
    @CacheLookup
    private WebElement 戻るボタン;

    /** 申請ボタン */
    @FindBy(id = "apply")
    @CacheLookup
    private WebElement 申請ボタン;

    /** 最申請ボタン */
    @FindBy(id = "reApply")
    @CacheLookup
    private WebElement 最申請ボタン;

    /**
     * PageFactoryを使用してWebElementをマッピングする
     * @param driver
     * @return 月報申請画面
     */
    public 月報申請画面 initialize(WebDriver driver) {
        return PageFactory.initElements(driver, this.getClass());
    }

    public WebElement 年月() {
        return this.年月;
    }

    public WebElement 月報ファイル() {
        return this.月報ファイル;
    }

    public WebElement フェイクファイルテキスト() {
        return this.フェイクファイルテキスト;
    }

    public WebElement ファイル選択ボタン() {
        return this.ファイル選択ボタン;
    }

    public WebElement 戻るボタン() {
        return this.戻るボタン;
    }

    public WebElement 申請ボタン() {
        return this.申請ボタン;
    }

    public WebElement 最申請ボタン() {
        return this.最申請ボタン;
    }

}
