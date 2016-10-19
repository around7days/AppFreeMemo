package rms.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UserRegistPageクラス
 */
public class UserRegistPage extends rms.selenium.page.AbstractCommonPage {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserRegistPage.class);

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

    /** 申請者ID1 */
    @FindBy(id = "approveUserId1")
    @CacheLookup
    private WebElement 申請者ID1;

    /** 申請者ID2 */
    @FindBy(id = "approveUserId2")
    @CacheLookup
    private WebElement 申請者ID2;

    /** 申請者ID3 */
    @FindBy(id = "approveUserId3")
    @CacheLookup
    private WebElement 申請者ID3;

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
    public UserRegistPage initialize() {
        return PageFactory.initElements(driver, this.getClass());
    }

    /* IE操作メソッド ----------------------------------------------------------------------------------------------- */
    /**
     * ユーザIDに値を入力します。
     * @param sendKeys
     */
    public void sendkeysユーザID(String sendKeys) {
        this.ユーザID.clear();
        this.ユーザID.sendKeys(sendKeys);
    }

    /**
     * パスワードに値を入力します。
     * @param sendKeys
     */
    public void sendkeysパスワード(String sendKeys) {
        this.パスワード.clear();
        this.パスワード.sendKeys(sendKeys);
    }

    /**
     * ユーザ名に値を入力します。
     * @param sendKeys
     */
    public void sendkeysユーザ名(String sendKeys) {
        this.ユーザ名.clear();
        this.ユーザ名.sendKeys(sendKeys);
    }

    /**
     * 部署IDを選択します。
     * @param index
     */
    public void selectByIndex部署ID(int index) {
        new Select(this.部署ID).selectByIndex(index);
    }

    /**
     * 部署IDを選択します。
     * @param value
     */
    public void selectByValue部署ID(String value) {
        new Select(this.部署ID).selectByValue(value);
    }

    /**
     * 部署ID を選択します。
     * @param visibleText
     */
    public void selectByVisibleText部署ID(String visibleText) {
        new Select(this.部署ID).selectByVisibleText(visibleText);
    }

    /**
     * 申請者ID1を選択します。
     * @param index
     */
    public void selectByIndex申請者ID1(int index) {
        new Select(this.申請者ID1).selectByIndex(index);
    }

    /**
     * 申請者ID1を選択します。
     * @param value
     */
    public void selectByValue申請者ID1(String value) {
        new Select(this.申請者ID1).selectByValue(value);
    }

    /**
     * 申請者ID1 を選択します。
     * @param visibleText
     */
    public void selectByVisibleText申請者ID1(String visibleText) {
        new Select(this.申請者ID1).selectByVisibleText(visibleText);
    }

    /**
     * 申請者ID2を選択します。
     * @param index
     */
    public void selectByIndex申請者ID2(int index) {
        new Select(this.申請者ID2).selectByIndex(index);
    }

    /**
     * 申請者ID2を選択します。
     * @param value
     */
    public void selectByValue申請者ID2(String value) {
        new Select(this.申請者ID2).selectByValue(value);
    }

    /**
     * 申請者ID2 を選択します。
     * @param visibleText
     */
    public void selectByVisibleText申請者ID2(String visibleText) {
        new Select(this.申請者ID2).selectByVisibleText(visibleText);
    }

    /**
     * 申請者ID3を選択します。
     * @param index
     */
    public void selectByIndex申請者ID3(int index) {
        new Select(this.申請者ID3).selectByIndex(index);
    }

    /**
     * 申請者ID3を選択します。
     * @param value
     */
    public void selectByValue申請者ID3(String value) {
        new Select(this.申請者ID3).selectByValue(value);
    }

    /**
     * 申請者ID3 を選択します。
     * @param visibleText
     */
    public void selectByVisibleText申請者ID3(String visibleText) {
        new Select(this.申請者ID3).selectByVisibleText(visibleText);
    }

    /**
     * 役割_申請者をクリックします。
     */
    public void click役割_申請者() {
        this.役割_申請者.click();
    }

    /**
     * 役割_承認者をクリックします。
     */
    public void click役割_承認者() {
        this.役割_承認者.click();
    }

    /**
     * 役割_管理者をクリックします。
     */
    public void click役割_管理者() {
        this.役割_管理者.click();
    }

    /**
     * 戻るボタンをクリックします。
     */
    public void click戻るボタン() {
        this.戻るボタン.click();
    }

    /**
     * 登録ボタンをクリックします。
     */
    public void click登録ボタン() {
        this.登録ボタン.click();
    }

    /**
     * 削除ボタンをクリックします。
     */
    public void click削除ボタン() {
        this.削除ボタン.click();
    }

    /**
     * 更新ボタンをクリックします。
     */
    public void click更新ボタン() {
        this.更新ボタン.click();
    }

}
