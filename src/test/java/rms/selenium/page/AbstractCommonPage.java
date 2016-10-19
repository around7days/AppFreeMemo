package rms.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/**
 * 共通情報ページクラス
 */
public class AbstractCommonPage extends selenium.base.AbstractSeleniumPage {

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

    /* IE操作メソッド ----------------------------------------------------------------------------------------------- */

    /**
     * ページ_Prevアンカーをクリックします。
     */
    public void clickページ_Prevアンカー() {
        this.ページ_Prevアンカー.click();
    }

    /**
     * ページ_Nextアンカーをクリックします。
     */
    public void clickページ_Nextアンカー() {
        this.ページ_Nextアンカー.click();
    }

    /**
     * ページ_テキストの値を取得します。
     * @return text
     */
    public String getTextページ_テキスト() {
        return this.ページ_テキスト.getText();
    }

    /**
     * メッセージ_成功の値を取得します。
     * @return text
     */
    public String getTextメッセージ_成功() {
        return this.メッセージ_成功.getText();
    }

    /**
     * メッセージ_エラーの値を取得します。
     * @return text
     */
    public String getTextメッセージ_エラー() {
        return this.メッセージ_エラー.getText();
    }

    /**
     * ヘッダ_メニューをクリックします。
     */
    public void clickヘッダ_メニュー() {
        this.ヘッダ_メニュー.click();
    }

    /**
     * ヘッダ_ログアウトをクリックします。
     */
    public void clickヘッダ_ログアウト() {
        this.ヘッダ_ログアウト.click();
    }

    /**
     * ヘッダ_ユーザ名の値を取得します。
     * @return text
     */
    public String getTextヘッダ_ユーザ名() {
        return this.ヘッダ_ユーザ名.getText();
    }

}
