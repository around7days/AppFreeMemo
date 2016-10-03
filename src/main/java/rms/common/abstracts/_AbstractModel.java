package rms.common.abstracts;

// package rms.common.abstracts;
//
// import java.time.LocalDateTime;
//
// import org.seasar.doma.Column;
// import org.seasar.doma.Entity;
// import org.seasar.doma.Version;
//
// import org.apache.commons.lang3.builder.ToStringBuilder;
// import org.apache.commons.lang3.builder.ToStringStyle;
//
/// **
// * @author
// */
// @Entity
// public abstract class AbstractModel {
//
// /** バージョン */
// @Version
// @Column(name = "version")
// private Integer version;
//
// /** 削除フラグ */
// @Column(name = "del_flg")
// private Integer delFlg;
//
// /** 登録日時 */
// @Column(name = "ins_date")
// private LocalDateTime insDate;
//
// /** 登録ID */
// @Column(name = "ins_id")
// private String insId;
//
// /** 更新日時 */
// @Column(name = "upd_date")
// private LocalDateTime updDate;
//
// /** 更新ID */
// @Column(name = "upd_id")
// private String updId;
//
// /**
// * バージョンを取得します.
// * @return バージョン
// */
// public Integer getVersion() {
// return version;
// }
//
// /**
// * バージョンを設定します.
// * @param version バージョン
// */
// public void setVersion(Integer version) {
// this.version = version;
// }
//
// /**
// * 削除フラグを取得します.
// * @return 削除フラグ
// */
// public Integer getDelFlg() {
// return delFlg;
// }
//
// /**
// * 削除フラグを設定します.
// * @param delFlg 削除フラグ
// */
// public void setDelFlg(Integer delFlg) {
// this.delFlg = delFlg;
// }
//
// /**
// * 登録日時を取得します.
// * @return 登録日時
// */
// public LocalDateTime getInsDate() {
// return insDate;
// }
//
// /**
// * 登録日時を設定します.
// * @param insDate 登録日時
// */
// public void setInsDate(LocalDateTime insDate) {
// this.insDate = insDate;
// }
//
// /**
// * 登録IDを取得します.
// * @return 登録ID
// */
// public String getInsId() {
// return insId;
// }
//
// /**
// * 登録IDを設定します.
// * @param insId 登録ID
// */
// public void setInsId(String insId) {
// this.insId = insId;
// }
//
// /**
// * 更新日時を取得します.
// * @return 更新日時
// */
// public LocalDateTime getUpdDate() {
// return updDate;
// }
//
// /**
// * 更新日時を設定します.
// * @param updDate 更新日時
// */
// public void setUpdDate(LocalDateTime updDate) {
// this.updDate = updDate;
// }
//
// /**
// * 更新IDを取得します.
// * @return 更新ID
// */
// public String getUpdId() {
// return updId;
// }
//
// /**
// * 更新IDを設定します.
// * @param updId 更新ID
// */
// public void setUpdId(String updId) {
// this.updId = updId;
// }
//
// @Override
// public String toString() {
// return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
// }
//
// }
