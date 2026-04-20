package greet.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import greet.demo.entity.UserEntity;
import greet.demo.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginUserDetailsService implements UserDetailsService {
	
	private final LoginUserRepository loginUserRepository;
	
	// ログインIdで認証するが、Securityからの呼出しメソッドはByUsernameとなっている
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserEntity loginUser = loginUserRepository.findByUserId(userId);
		if (loginUser == null) {
			throw  new UsernameNotFoundException("Not found user");
		}
		return new LoginUserDetails(loginUser);                		
		
	}

}
