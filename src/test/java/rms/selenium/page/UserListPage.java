package rms.selenium.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UserListPageクラス
 */
public class UserListPage extends rms.selenium.page.AbstractCommonPage {

    /** ロガー */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserListPage.class);

    /* 項目変数宣言 ------------------------------------------------------------------------------------------------- */
    /** 検索条件_ユーザID */
    @FindBy(id = "condition.userId")
    @CacheLookup
    private WebElement 検索条件_ユーザID;

    /** 検索条件_ユーザ名 */
    @FindBy(id = "condition.userNm")
    @CacheLookup
    private WebElement 検索条件_ユーザ名;

    /** 戻るボタン */
    @FindBy(id = "back")
    @CacheLookup
    private WebElement 戻るボタン;

    /** 検索ボタン */
    @FindBy(id = "search")
    @CacheLookup
    private WebElement 検索ボタン;

    /** 新規ボタン */
    @FindBy(id = "insert")
    @CacheLookup
    private WebElement 新規ボタン;

    /** 検索結果_選択ボタン */
    @FindBy(css = "#resultTable button[name='select']")
    @CacheLookup
    private List<WebElement> 検索結果_選択ボタン;

    /* 共通メソッド宣言 --------------------------------------------------------------------------------------------- */
    /**
     * PageFactoryを使用してWebElementをマッピングする
     * @return Page
     */
    public UserListPage initialize() {
        return PageFactory.initElements(driver, this.getClass());
    }

    /* IE操作メソッド ----------------------------------------------------------------------------------------------- */
    /**
     * 検索条件_ユーザIDに値を入力します。
     * @param sendKeys
     */
    public void sendkeys検索条件_ユーザID(String sendKeys) {
        this.検索条件_ユーザID.clear();
        this.検索条件_ユーザID.sendKeys(sendKeys);
    }

    /**
     * 検索条件_ユーザ名に値を入力します。
     * @param sendKeys
     */
    public void sendkeys検索条件_ユーザ名(String sendKeys) {
        this.検索条件_ユーザ名.clear();
        this.検索条件_ユーザ名.sendKeys(sendKeys);
    }

    /**
     * 戻るボタンをクリックします。
     */
    public void click戻るボタン() {
        this.戻るボタン.click();
    }

    /**
     * 検索ボタンをクリックします。
     */
    public void click検索ボタン() {
        this.検索ボタン.click();
    }

    /**
     * 新規ボタンをクリックします。
     */
    public void click新規ボタン() {
        this.新規ボタン.click();
    }

    /**
     * 検索結果_選択ボタンをクリックします。
     */
    public void click検索結果_選択ボタン() {
        click検索結果_選択ボタン(0);
    }

    /**
     * 検索結果_選択ボタンをクリックします。
     * @param index
     */
    public void click検索結果_選択ボタン(int index) {
        this.検索結果_選択ボタン.get(index).click();
    }

}
