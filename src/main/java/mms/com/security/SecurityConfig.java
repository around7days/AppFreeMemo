package mms.com.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security設定クラス.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http://qiita.com/nvtomo1029/items/8827d95327b647a6cf50

        /* 許可設定 */
        http.authorizeRequests().antMatchers("/", "/login", "logout").permitAll(); // "/"は全ユーザー許可
        http.authorizeRequests().anyRequest().authenticated(); //
        // それ以外は全て認証無しの場合アクセス拒否

        /* ログイン設定 */
        // http.formLogin()
        // .loginPage("/") // ログインフォームのパス
        // .loginProcessingUrl("/login") // 認証処理のパス
        // .usernameParameter("userId") // ユーザー名のパラメータ名
        // .passwordParameter("password"); // パスワードのパラメータ名

        /* ログアウト設定 */
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウト処理のパス
            .logoutSuccessUrl("/") // ログアウト完了時のパス
            .deleteCookies("JSESSIONID") // cookiesの削除
            .invalidateHttpSession(true); // セッション破棄

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト設定
        // 静的リソース(images、css、javascript等)に対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers("/fw/**", "/js/**", "/css/**", "/image/**");
    }

    // @Autowired
    // public void configAuthentication(AuthenticationManagerBuilder auth)
    // throws Exception {
    // auth.jdbcAuthentication()
    // .dataSource(dataSource)
    // .usersByUsernameQuery("select id, password, enabled from user where id =
    // ?")
    // .authoritiesByUsernameQuery("select id, role from user_role where id =
    // ?");
    // }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws
    // Exception {
    // auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    // }
}