package rms.test.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザ登録画面クラス
 */
public class ユーザ登録画面 extends rms.test.selenium.page.共通部品 {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ユーザ登録画面.class);

    /** ユーザID */
    @FindBy(id = "userId")
    @CacheLookup
    private WebElement ユーザID;

    /** パスワード */
    @FindBy(id = "password")
    @CacheLookup
    private WebElement パスワード;

    /** ユーザ名 */
    @FindBy(id = "userNm")
    @CacheLookup
    private WebElement ユーザ名;

    /** メールアドレス */
    @FindBy(id = "email")
    @CacheLookup
    private WebElement メールアドレス;

    /** 部署セレクト */
    @FindBy(id = "departmentId")
    @CacheLookup
    private WebElement 部署セレクト;

    /** 承認者１セレクト */
    @FindBy(id = "approveUserId1")
    @CacheLookup
    private WebElement 承認者１セレクト;

    /** 承認者２セレクト */
    @FindBy(id = "approveUserId2")
    @CacheLookup
    private WebElement 承認者２セレクト;

    /** 承認者３セレクト */
    @FindBy(id = "approveUserId3")
    @CacheLookup
    private WebElement 承認者３セレクト;

    /** 役割_申請者チェック */
    @FindBy(id = "roleApplyFlg")
    @CacheLookup
    private WebElement 役割_申請者チェック;

    /** 役割_承認者チェック */
    @FindBy(id = "roleApproveFlg")
    @CacheLookup
    private WebElement 役割_承認者チェック;

    /** 役割_管理者チェック */
    @FindBy(id = "roleAdminFlg")
    @CacheLookup
    private WebElement 役割_管理者チェック;

    /** 戻るボタン */
    @FindBy(id = "back")
    @CacheLookup
    private WebElement 戻るボタン;

    /** 登録ボタン */
    @FindBy(id = "insert")
    @CacheLookup
    private WebElement 登録ボタン;

    /** 削除ボタン */
    @FindBy(id = "delete")
    @CacheLookup
    private WebElement 削除ボタン;

    /** 更新ボタン */
    @FindBy(id = "update")
    @CacheLookup
    private WebElement 更新ボタン;

    /**
     * PageFactoryを使用してWebElementをマッピングする
     * @param driver
     * @return ユーザ登録画面
     */
    public ユーザ登録画面 initialize(WebDriver driver) {
        return PageFactory.initElements(driver, this.getClass());
    }

    public WebElement ユーザID() {
        return this.ユーザID;
    }

    public WebElement パスワード() {
        return this.パスワード;
    }

    public WebElement ユーザ名() {
        return this.ユーザ名;
    }

    public WebElement メールアドレス() {
        return this.メールアドレス;
    }

    public WebElement 部署セレクト() {
        return this.部署セレクト;
    }

    public WebElement 承認者１セレクト() {
        return this.承認者１セレクト;
    }

    public WebElement 承認者２セレクト() {
        return this.承認者２セレクト;
    }

    public WebElement 承認者３セレクト() {
        return this.承認者３セレクト;
    }

    public WebElement 役割_申請者チェック() {
        return this.役割_申請者チェック;
    }

    public WebElement 役割_承認者チェック() {
        return this.役割_承認者チェック;
    }

    public WebElement 役割_管理者チェック() {
        return this.役割_管理者チェック;
    }

    public WebElement 戻るボタン() {
        return this.戻るボタン;
    }

    public WebElement 登録ボタン() {
        return this.登録ボタン;
    }

    public WebElement 削除ボタン() {
        return this.削除ボタン;
    }

    public WebElement 更新ボタン() {
        return this.更新ボタン;
    }

}
