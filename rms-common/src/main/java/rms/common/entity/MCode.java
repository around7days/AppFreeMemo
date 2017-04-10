package rms.common.entity;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

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

    public String getCodeKbn() {
        return codeKbn;
    }

    public void setCodeKbn(String codeKbn) {
        this.codeKbn = codeKbn;
    }

    public String getCodeKbnNm() {
        return codeKbnNm;
    }

    public void setCodeKbnNm(String codeKbnNm) {
        this.codeKbnNm = codeKbnNm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    public LocalDateTime getInsDate() {
        return insDate;
    }

    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

    public LocalDateTime getUpdDate() {
        return updDate;
    }

    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    public String getUpdId() {
        return updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}