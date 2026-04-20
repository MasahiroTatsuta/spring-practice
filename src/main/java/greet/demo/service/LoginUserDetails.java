package greet.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import greet.demo.entity.UserEntity;

public class LoginUserDetails implements UserDetails {
	
	private final UserEntity loginUser;

	public LoginUserDetails(UserEntity loginUser) {
		this.loginUser = loginUser;
	}

	// ユーザーの権限
	// Spring Securityでは「ROLE_」プレフィックスを付けた文字列をロールとして扱う
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return AuthorityUtils.createAuthorityList("ROLE_" + loginUser.getRole());
	}

	public String getUserId() {
		return loginUser.getUserId();
	}
	//　パスワード
	@Override
	public String getPassword() {
	    return loginUser.getPassword();
	}

	@Override
	public String getUsername() {
	    return loginUser.getUsername();
	}

	// アカウントの期限切れ（期限切れなし）
	@Override
	public boolean isAccountNonExpired() {
	    return true;
	}

	// アカウントのロック状態（ロックは使用しない）
	@Override
	public boolean isAccountNonLocked() {
	    return true;
	}

	// パスワードの期限切れ（期限切れなし）
	@Override
	public boolean isCredentialsNonExpired() {
	    return true;
	}

	// 有効なユーザー
	@Override
	public boolean isEnabled() {
	    return true;
	}
}
