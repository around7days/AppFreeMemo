package rms.test.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ユーザ登録画面クラス
 */
public class ユーザ登録画面 extends rms.test.selenium.page.CommonParts {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ユーザ登録画面.class);

    /* 項目変数宣言 ------------------------------------------------------------------------------------------------- */
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

    /** 部署ID */
    @FindBy(id = "departmentId")
    @CacheLookup
    private WebElement 部署ID;

    /** 承認者ID1 */
    @FindBy(id = "approveUserId1")
    @CacheLookup
    private WebElement 承認者ID1;

    /** 承認者ID2 */
    @FindBy(id = "approveUserId2")
    @CacheLookup
    private WebElement 承認者ID2;

    /** 承認者ID3 */
    @FindBy(id = "approveUserId3")
    @CacheLookup
    private WebElement 承認者ID3;

    /** 役割_申請者 */
    @FindBy(id = "roleApplyFlg")
    @CacheLookup
    private WebElement 役割_申請者;

    /** 役割_承認者 */
    @FindBy(id = "roleApproveFlg")
    @CacheLookup
    private WebElement 役割_承認者;

    /** 役割_管理者 */
    @FindBy(id = "roleAdminFlg")
    @CacheLookup
    private WebElement 役割_管理者;

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

    /* 共通メソッド宣言 --------------------------------------------------------------------------------------------- */
    /**
     * PageFactoryを使用してWebElementをマッピングする
     * @return Page
     */
    public ユーザ登録画面 initialize() {
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

    public WebElement 部署ID() {
        return this.部署ID;
    }

    public WebElement 承認者ID1() {
        return this.承認者ID1;
    }

    public WebElement 承認者ID2() {
        return this.承認者ID2;
    }

    public WebElement 承認者ID3() {
        return this.承認者ID3;
    }

    public WebElement 役割_申請者() {
        return this.役割_申請者;
    }

    public WebElement 役割_承認者() {
        return this.役割_承認者;
    }

    public WebElement 役割_管理者() {
        return this.役割_管理者;
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

    /* IE操作メソッド ----------------------------------------------------------------------------------------------- */

}
