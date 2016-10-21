package rms.test.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CommonPartsクラス
 */
public class CommonParts extends selenium.base.AbstractSeleniumTest {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(CommonParts.class);

    /* 項目変数宣言 ------------------------------------------------------------------------------------------------- */
    /** ページ_Prevアンカー */
    @FindBy(id = "pagePrev")
    @CacheLookup
    private WebElement ページ_Prevアンカー;

    /** ページ_Nextアンカー */
    @FindBy(id = "pageNext")
    @CacheLookup
    private WebElement ページ_Nextアンカー;

    /** ページ_テキスト */
    @FindBy(id = "pageText")
    @CacheLookup
    private WebElement ページ_テキスト;

    /** メッセージ_成功 */
    @FindBy(id = "messageSuccess")
    @CacheLookup
    private WebElement メッセージ_成功;

    /** メッセージ_エラー */
    @FindBy(id = "messageDanger")
    @CacheLookup
    private WebElement メッセージ_エラー;

    /** ヘッダ_メニュー */
    @FindBy(id = "navbarMenu")
    @CacheLookup
    private WebElement ヘッダ_メニュー;

    /** ヘッダ_ログアウト */
    @FindBy(id = "navbarLogout")
    @CacheLookup
    private WebElement ヘッダ_ログアウト;

    /** ヘッダ_ユーザ名 */
    @FindBy(id = "navbarUserNm")
    @CacheLookup
    private WebElement ヘッダ_ユーザ名;

    /* 共通メソッド宣言 --------------------------------------------------------------------------------------------- */
    public WebElement ページ_Prevアンカー() {
        return this.ページ_Prevアンカー;
    }

    public WebElement ページ_Nextアンカー() {
        return this.ページ_Nextアンカー;
    }

    public WebElement ページ_テキスト() {
        return this.ページ_テキスト;
    }

    public WebElement メッセージ_成功() {
        return this.メッセージ_成功;
    }

    public WebElement メッセージ_エラー() {
        return this.メッセージ_エラー;
    }

    public WebElement ヘッダ_メニュー() {
        return this.ヘッダ_メニュー;
    }

    public WebElement ヘッダ_ログアウト() {
        return this.ヘッダ_ログアウト;
    }

    public WebElement ヘッダ_ユーザ名() {
        return this.ヘッダ_ユーザ名;
    }

    /* IE操作メソッド ----------------------------------------------------------------------------------------------- */

}
