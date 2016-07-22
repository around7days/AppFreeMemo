package rms.com.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rms.domain.com.entity.MUser;
import rms.domain.com.entity.VMUserRole;
import rms.domain.com.repository.MUserDao;
import rms.domain.mst.user.repository.UserSelectDao;
import rms.web.login.LoginController;
import rms.web.menu.MenuController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * Spring Security設定クラス.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** 認証 マッピングURL */
    public static final String AUTH_MAPPING_URL = "/auth";
    /** ログイン マッピングURL */
    public static final String LOGIN_MAPPING_URL = LoginController.MAPPING_URL;
    /** ログアウト マッピングURL */
    public static final String LOGOUT_MAPPING_URL = "/logout";
    /** エラー マッピングURL */
    public static final String ERROR_MAPPING_URL = ErrorControllerImpl.MAPPING_URL;
    /** メニュー マッピングURL */
    public static final String MENU_MAPPING_URL = MenuController.MAPPING_URL;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http://qiita.com/nvtomo1029/items/8827d95327b647a6cf50

        /* ログイン前の認証設定 */
        // OK(アクセス許可)
        http.authorizeRequests()
            .antMatchers("/", AUTH_MAPPING_URL, LOGIN_MAPPING_URL, LOGOUT_MAPPING_URL, ERROR_MAPPING_URL)
            .permitAll();
        // NG（それ以外は全て認証無しの場合アクセス拒否）
        http.authorizeRequests().anyRequest().authenticated();

        /* セッション設定 */
        // http.sessionManagement().invalidSessionUrl("/");

        /* ログイン設定 */
        http.formLogin()
            .loginPage(LOGIN_MAPPING_URL) // ログインフォームのパス
            .loginProcessingUrl(AUTH_MAPPING_URL) // 認証処理のパス
            .usernameParameter("userId") // 認証ユーザ名のパラメータ名
            .passwordParameter("password") // 認証パスワードのパラメータ名
            .defaultSuccessUrl(MENU_MAPPING_URL) // 認証成功時のパス
            .failureUrl(LOGIN_MAPPING_URL + "?error") // 認証失敗時のパス
        ;

        /* ログアウト設定 */
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_MAPPING_URL)) // ログアウト処理のパス
            .logoutSuccessUrl(LoginController.MAPPING_URL) // ログアウト完了時のパス
            .deleteCookies("JSESSIONID") // cookiesの削除
            .invalidateHttpSession(true) // セッション破棄
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト設定
        // 静的リソース(images、css、javascript等)に対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers("/fw/**", "/js/**", "/css/**", "/image/**", "/webjars/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 独自認証
        auth.userDetailsService(userDetailsService);
    }
}

/**
 * 独自認証処理
 * @author
 */
@Component
class UserDetailsServiceImpl implements UserDetailsService {

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