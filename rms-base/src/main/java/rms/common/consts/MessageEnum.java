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
    /** 登録が完了しました */
    info001,
    /** 更新が完了しました */
    info002,
    /** 削除が完了しました */
    info003,
    /** 申請が完了しました */
    info004,
    /** 承認が完了しました */
    info005,
    /** 否認が完了しました */
    info006,

    /*
     * meessage error
     */
    /** {0}が重複しています */
    error001,
    /** 該当データは既に他のユーザに更新されています */
    error002,
    /** 対象年月の月報は既に申請されています */
    error003,
    /** 承認者１～４に同じ承認者は設定できません */
    error004,
    /** 役割が申請者の場合、承認者は必須です */
    error005,
    /** 検索結果が見つかりません */
    error006,
    /** 対象年月の月報は{0}以降から申請可能です */
    error007,
    /** 月報ファイル名のフォーマットが正しくありません(yyyymm_userId_userNm.xlsx) */
    error008,
    /** 月報情報が見つかりません */
    error009,
    /** 承認権限がありません */
    error010,
    /** ログインに失敗しました */
    error011,
    /** 月報ファイルが見つかりません [ 対象年月：{0} 対象ユーザ名：{1} ] */
    error012,
    /** パラメータにバッチIDが設定されていません */
    error013,
    /** バッチIDが不正です */
    error014,
    /** バッチIDに対するパラメータ数が不正です */
    error015,
    /** バッチIDに対するパラメータが不正です */
    error016,
    /** {}の月報初期データは{}以降に作成可能です */
    error017,

}
