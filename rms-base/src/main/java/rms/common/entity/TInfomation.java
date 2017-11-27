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
 * TInfomationクラス
 */
@Entity(listener = TInfomationListener.class)
@Table(name = "t_infomation")
public class TInfomation {
    /** 連番 */
    @Id
    @Column(name = "seq")
    private Integer seq;
    /** お知らせ情報 */
    @Column(name = "info")
    private String info;
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

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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