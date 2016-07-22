package rms.com.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rms.domain.com.entity.MUser;
import rms.domain.com.entity.VMUserRole;
import rms.domain.com.repository.MUserDao;
import rms.domain.mst.user.repository.UserSelectDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 独自認証処理
 * @author
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MUserDao mUserDao;

    @Autowired
    private UserSelectDao userSelectDao;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        // ユーザ情報の取得
        MUser mUser = mUserDao.selectById(id);
        if (mUser == null) {
            throw new UsernameNotFoundException("ログインに失敗しました");
        }

        // ユーザ役割情報の取得
        List<VMUserRole> mUserRoleList = userSelectDao.userRoleListByUserId(id);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (VMUserRole mUserRole : mUserRoleList) {
            authorities.add(new SimpleGrantedAuthority(mUserRole.getRole()));
        }

        // 認証ユーザ情報の返却
        return new UserInfo(mUser, authorities);
    }
}
