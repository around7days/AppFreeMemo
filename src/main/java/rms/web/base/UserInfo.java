package rms.web.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rms.com.consts.MRoleConst;
import rms.domain.com.entity.MUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 認証ユーザの情報を格納するクラス
 */
public class UserInfo extends User {
    private static final long serialVersionUID = 1L;

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserInfo.class);

    /*
     * ユーザ情報
     */
    /** ユーザID */
    private String userId;

    /** ユーザ名 */
    private String userNm;

    /** ユーザ役割名一覧 */
    private List<String> roleList = new ArrayList<>();

    /**
     * コンストラクタ
     * @param user
     * @param authorities
     */
    public UserInfo(MUser user, Collection<GrantedAuthority> authorities) {
        // スーパークラスのユーザID、パスワードに値をセットする
        // 実際の認証はスーパークラスのユーザID、パスワードで行われる
        super(user.getUserId(), user.getPassword(), authorities);
        this.userId = user.getUserId();
        this.userNm = user.getUserNm();
        authorities.forEach(auth -> this.roleList.add(auth.toString()));

        logger.debug("ユーザ情報 -> {}", this.toString());
    }

    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * 役割：申請者？
     * @return
     */
    public boolean isRoleApplicant() {
        return roleList.contains(MRoleConst.APPLICANT);
    }

    /**
     * 役割：承認者？
     * @return
     */
    public boolean isRoleApprover() {
        return roleList.contains(MRoleConst.APPROVER);
    }

    /**
     * 役割：管理者？
     * @return
     */
    public boolean isRoleAdmin() {
        return roleList.contains(MRoleConst.ADMIN);
    }

    /**
     * ユーザIDを取得します。
     * @deprecated IDと名称が紛らわしいので非推奨(UserInfo#getUserId()を仕様すること)
     * @see UserInfo#getUserId()
     */
    @Deprecated
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}