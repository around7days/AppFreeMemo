package rms.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import rms.common.auth.UserDetailsServiceImpl;

/**
 * Spring Security設定クラス.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(1) // TODO gradleからのテスト実行時のみOrder指定が無いとエラーになる・・・
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /** ベース マッピングURL */
    public static final String BASE_MAPPING_URL = "/";
    /** ログイン画面 マッピングURL */
    public static final String LOGIN_MAPPING_URL = "/login";
    /** ログイン認証 マッピングURL */
    public static final String AUTH_MAPPING_URL = "/auth";
    /** ログアウト処理 マッピングURL */
    public static final String LOGOUT_MAPPING_URL = "/logout";
    /** エラー マッピングURL */
    public static final String ERROR_MAPPING_URL = "/error";
    /** メニュー マッピングURL */
    public static final String MENU_MAPPING_URL = "/menu";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /* ログイン前の認証設定 */
        // OK(アクセス許可)
        http.authorizeRequests()
            .antMatchers(BASE_MAPPING_URL, //
                         LOGIN_MAPPING_URL, //
                         AUTH_MAPPING_URL, //
                         LOGOUT_MAPPING_URL, //
                         ERROR_MAPPING_URL) //
            .permitAll() //
        ;
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
            .logoutSuccessUrl(LOGIN_MAPPING_URL) // ログアウト完了時のパス
            .deleteCookies("JSESSIONID") // cookiesの削除
            .invalidateHttpSession(true) // セッション破棄
        ;

        /* アクセス制限設定 */
        // http.exceptionHandling().accessDeniedPage(ERROR_MAPPING_URL);
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
