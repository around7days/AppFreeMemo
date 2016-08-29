package rms.common.consts;

//package rms.common.consts;
//
///**
// * 役割マスタ定義
// * @author
// */
//public enum MRoleEnum {
//    /** 申請者 */
//    APPLY("1", "ROLE_APPLY"),
//    /** 承認者 */
//    APPROVE("2", "ROLE_APPROVE"),
//    /** 管理者 */
//    ADMIN("3", "ROLE_ADMIN");
//
//    /** 役割ID */
//    private String roleId;
//    /** 役割 */
//    private String role;
//
//    /**
//     * コンストラクタ
//     * @param roleId
//     * @param role
//     */
//    MRoleEnum(String roleId, String role) {
//        this.roleId = roleId;
//        this.role = role;
//    }
//
//    /**
//     * 文字列からEnumへ変換
//     * @param str
//     * @return
//     */
//    public static MRoleEnum getEnum(String str) {
//        if (str == null)
//            return null;
//        for (MRoleEnum mRoleEnum : MRoleEnum.values()) {
//            if (mRoleEnum.name().toLowerCase().equals(str.toLowerCase())) {
//                return mRoleEnum;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 役割IDを取得します。
//     * @return 役割ID
//     */
//    public String getRoleId() {
//        return roleId;
//    }
//
//    /**
//     * 役割IDを設定します。
//     * @param roleId 役割ID
//     */
//    public void setRoleId(String roleId) {
//        this.roleId = roleId;
//    }
//
//    /**
//     * 役割を取得します。
//     * @return 役割
//     */
//    public String getRole() {
//        return role;
//    }
//
//    /**
//     * 役割を設定します。
//     * @param role 役割
//     */
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//}
