package rms.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 共通部品クラス
 */
public class CommonPage {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(CommonPage.class);

    /** ページ_Prev */
    @FindBy(id = "pagePrev")
    @CacheLookup
    private WebElement ページ_Prev;

    /** ページ_Next */
    @FindBy(id = "pageNext")
    @CacheLookup
    private WebElement ページ_Next;

    /** ページ_結果 */
    @FindBy(id = "pageResult")
    @CacheLookup
    private WebElement ページ_結果;

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

    /** メッセージ_通常 */
    @FindBy(id = "messageSuccess")
    @CacheLookup
    private WebElement メッセージ_通常;

    /** メッセージ_エラー */
    @FindBy(id = "messageDanger")
    @CacheLookup
    private WebElement メッセージ_エラー;

    public WebElement ページ_Prev() {
        return this.ページ_Prev;
    }

    public WebElement ページ_Next() {
        return this.ページ_Next;
    }

    public WebElement ページ_結果() {
        return this.ページ_結果;
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

    public WebElement メッセージ_通常() {
        return this.メッセージ_通常;
    }

    public WebElement メッセージ_エラー() {
        return this.メッセージ_エラー;
    }

}
