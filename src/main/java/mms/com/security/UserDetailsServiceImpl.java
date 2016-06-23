package mms.com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import mms.com.doma.dao.MUserDao;
import mms.com.doma.entity.MUser;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MUserDao mUserDao;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        MUser mUser = mUserDao.selectById(id);
        if (mUser == null) {
            // 例外はSpringSecurityにあったものを適当に使用
            throw new UsernameNotFoundException("ログインに失敗しました");
        }

        return new LoginUserInfo(mUser);
    }
}