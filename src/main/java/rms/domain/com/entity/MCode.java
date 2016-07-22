package rms.domain.com.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * MCodeクラス
 */
@Entity(listener = MCodeListener.class)
@Table(name = "m_code")
public class MCode {

    /** コード区分 */
    @Id
    @Column(name = "code_kbn")
    private String codeKbn;

    /** コード区分名称 */
    @Column(name = "code_kbn_nm")
    private String codeKbnNm;

    /** コード */
    @Id
    @Column(name = "code")
    private String code;

    /** コード名称 */
    @Column(name = "code_nm")
    private String codeNm;

    /** 属性１ */
    @Column(name = "attr1")
    private String attr1;

    /** 属性２ */
    @Column(name = "attr2")
    private String attr2;

    /** 属性３ */
    @Column(name = "attr3")
    private String attr3;

    /** 説明 */
    @Column(name = "description")
    private String description;

    /** バージョン */
    @Version
    @Column(name = "version")
    private Integer version;

    /** 削除フラグ */
    @Column(name = "del_flg")
    private Integer delFlg;

    /** 登録日時 */
    @Column(name = "ins_date")
    private LocalDateTime insDate;

    /** 登録ID */
    @Column(name = "ins_id")
    private String insId;

    /** 更新日時 */
    @Column(name = "upd_date")
    private LocalDateTime updDate;

    /** 更新ID */
    @Column(name = "upd_id")
    private String updId;

    /**
     * コード区分を取得します.
     * @return コード区分
     */
    public String getCodeKbn() {
        return codeKbn;
    }

    /**
     * コード区分を設定します.
     * @param codeKbn コード区分
     */
    public void setCodeKbn(String codeKbn) {
        this.codeKbn = codeKbn;
    }

    /**
     * コード区分名称を取得します.
     * @return コード区分名称
     */
    public String getCodeKbnNm() {
        return codeKbnNm;
    }

    /**
     * コード区分名称を設定します.
     * @param codeKbnNm コード区分名称
     */
    public void setCodeKbnNm(String codeKbnNm) {
        this.codeKbnNm = codeKbnNm;
    }

    /**
     * コードを取得します.
     * @return コード
     */
    public String getCode() {
        return code;
    }

    /**
     * コードを設定します.
     * @param code コード
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * コード名称を取得します.
     * @return コード名称
     */
    public String getCodeNm() {
        return codeNm;
    }

    /**
     * コード名称を設定します.
     * @param codeNm コード名称
     */
    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    /**
     * 属性１を取得します.
     * @return 属性１
     */
    public String getAttr1() {
        return attr1;
    }

    /**
     * 属性１を設定します.
     * @param attr1 属性１
     */
    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    /**
     * 属性２を取得します.
     * @return 属性２
     */
    public String getAttr2() {
        return attr2;
    }

    /**
     * 属性２を設定します.
     * @param attr2 属性２
     */
    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    /**
     * 属性３を取得します.
     * @return 属性３
     */
    public String getAttr3() {
        return attr3;
    }

    /**
     * 属性３を設定します.
     * @param attr3 属性３
     */
    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    /**
     * 説明を取得します.
     * @return 説明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明を設定します.
     * @param description 説明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * バージョンを取得します.
     * @return バージョン
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * バージョンを設定します.
     * @param version バージョン
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 削除フラグを取得します.
     * @return 削除フラグ
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * 削除フラグを設定します.
     * @param delFlg 削除フラグ
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 登録日時を取得します.
     * @return 登録日時
     */
    public LocalDateTime getInsDate() {
        return insDate;
    }

    /**
     * 登録日時を設定します.
     * @param insDate 登録日時
     */
    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    /**
     * 登録IDを取得します.
     * @return 登録ID
     */
    public String getInsId() {
        return insId;
    }

    /**
     * 登録IDを設定します.
     * @param insId 登録ID
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 更新日時を取得します.
     * @return 更新日時
     */
    public LocalDateTime getUpdDate() {
        return updDate;
    }

    /**
     * 更新日時を設定します.
     * @param updDate 更新日時
     */
    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    /**
     * 更新IDを取得します.
     * @return 更新ID
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 更新IDを設定します.
     * @param updId 更新ID
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}