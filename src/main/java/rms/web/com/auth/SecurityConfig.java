package rms.web.com.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import rms.com.doma.dao.MUserDao;
import rms.com.doma.entity.MUser;

/**
 * Spring Security設定クラス.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http://qiita.com/nvtomo1029/items/8827d95327b647a6cf50

        /* ログイン前の認証設定 */
        // OK(アクセス許可)
        http.authorizeRequests().antMatchers("/", "/login**", "/logout", "/error").permitAll();
        // NG（それ以外は全て認証無しの場合アクセス拒否）
        http.authorizeRequests().anyRequest().authenticated();

        /* セッション設定 */
        // http.sessionManagement().invalidSessionUrl("/");

        /* ログイン設定 */
        http.formLogin()
            .loginPage("/login") // ログインフォームのパス
            .loginProcessingUrl("/login_auth") // 認証処理のパス
            .usernameParameter("userId") // ユーザ名のパラメータ名
            .passwordParameter("password") // パスワードのパラメータ名
            .defaultSuccessUrl("/menu") // 認証成功時のパス
            .failureUrl("/login_error") // 認証失敗時のパス
        ;

        /* ログアウト設定 */
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウト処理のパス
            .logoutSuccessUrl("/login") // ログアウト完了時のパス
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

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        MUser mUser = mUserDao.selectById(id);
        if (mUser == null) {
            throw new UsernameNotFoundException("ログインに失敗しました");
        }

        return new UserInfo(mUser);
    }
}