package rms.common.consts;

/**
 * メッセージ定義<br>
 * message.propertiesと連動
 * @author
 */
public enum MessageEnum {

    /*
     * meessage info
     */
    info001, // 登録が完了しました
    info002, // 更新が完了しました
    info003, // 削除が完了しました
    info004, // 申請が完了しました
    info005, // 承認が完了しました
    info006, // 否認が完了しました

    /*
     * meessage error
     */
    error001, // {0}が重複しています
    error002, // 該当データは既に他のユーザに更新されています
    error003, // 対象年月の月報は既に申請されています
    error004, // 承認者１～３に同じ承認者は設定できません
    error005, // 役割が申請者の場合、承認者３は必須です
    error006, // 検索結果が見つかりません
    error007, // 対象年月の月報は{0}以降から申請可能です
    error008, // 月報ファイル名のフォーマットが正しくありません(yyyymm_userId_userNm.xlsx)
    error009, // 月報情報が見つかりません
    error010, // 承認権限がありません
}
