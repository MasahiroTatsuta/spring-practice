package greet.demo;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.*;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();				// パスワードのハッシュ方法
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login					// フォーム認証の設定記述開始
				.loginPage("/login")			    	// ログイン画面のURL。この指定がないとデフォルトのログインページが表示される
				.loginProcessingUrl("/login")			// ユーザー名・パスワードの送信先URL（POST先）
				.usernameParameter("user_id")			// ユーザー名のリクエストパラメータp名
				.defaultSuccessUrl("/",true)			// ログイン認証成功時の遷移先URL
				.failureUrl("/login?error=true")		// ログイン認証失敗時の遷移先URL
				.permitAll()
		).logout(logout -> logout						// ログアウトの設定記述開始
				.logoutSuccessUrl("/")					// ログアウト成功時のリダイレクト先URL
		).authorizeHttpRequests(authz -> authz			// URL毎の認可設定記述開始
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
					.permitAll()						// "css/**"などはログイン無しでもアクセス可能
				.requestMatchers(antMatcher("/")).permitAll()		// ルートは誰でもアクセス可
				.requestMatchers("/input","/user/**")		
					.hasRole("ADMIN")					// "/input"はROLE_ADMINのみアクセス可能				
				.anyRequest().authenticated()			// 他のURLはログイン後のみアクセス可能
		);
		return http.build();
	}
}
