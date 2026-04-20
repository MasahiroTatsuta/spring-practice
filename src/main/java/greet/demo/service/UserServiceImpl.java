package greet.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import greet.demo.entity.UserEntity;
import greet.demo.form.UserForm;
import greet.demo.misc.Password;
import greet.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	//
	// 全ユーザー情報の読取り
	//
	@Override
	public List<UserForm> getUsers() {
		// 戻りオブジェクトの生成
		List<UserForm> usersList = new ArrayList<UserForm>();
		// 全レコードをユーザーId でソートして読み取る
		Optional<List<UserEntity>> userRecords = userRepository.findAllByOrderByUserId();
		// 1 レコード以上あれば
		if (userRecords.isPresent()) {
			// レコードをフォーム形式に変更
			List<UserEntity> users = userRecords.get();
			for (var user:users) {
				usersList.add(user.toForm());
			}
		}
		return usersList;
	}
	
	//
	// ID で指定したユーザー情報の読取り
	//
	@Override
	public Optional<UserForm> getUser(Integer id) {
		Optional<UserEntity> user = userRepository.findById(id);
		if (user.isPresent()) {
			return Optional.of(user.orElse(null).toForm());
		} else {
			return Optional.ofNullable(null);
		}
	}
	
	//
	// ユーザーの新規登録
	// 戻り値: == "OK" (正常終了)
	// == "error:" (エラー : エラーメッセージ)
	// == "password:" (パスワードエラー: エラーメッセージ)
	@Override
	public String insertUser(UserForm userForm) {
		// 同じユーザーID が登録されていないことを確認
		String userId = userForm.getUserId();
		Optional<UserEntity> userRecord = userRepository.findByUserId(userId);
		if (userRecord.isPresent()) {
			return String.format("error:同じユーザーIDが登録済みです。(ユーザーID:%s)。", userId);
		}
		// Entity 形式に変更
		var userEntity = userForm.toEntity();
		
		// パスワードのチェック
		var password = userForm.getPassword();
		var returnMessage = new Password().check(password);
		// OK であれば
		if (returnMessage == "OK") {
			// パスワードをハッシュ化して設定
			userEntity.setPassword(passwordEncoder.encode(password));
		} else {
			// パスワードの強度に問題あれば、エラーメッセージを返す
			return returnMessage;
		}
		
		// 新規登録
		userRepository.save(userEntity);
 		return "OK";
	}
	
	//
	// ユーザーレコードを更新
	// 戻り値: == "OK" (正常終了)
	// == "error:" (エラー : エラーメッセージ)
	// == "password:" (パスワードエラー: エラーメッセージ)
	//
	@Override
	public String updateUser(UserForm userForm) {
		Integer id = userForm.getId();
		if (id == null) {
			return "error:ユーザーIdが指定されずに更新しようとしています。";
		}
		Optional<UserEntity> userRecord = userRepository.findById(id);
		if (!userRecord.isPresent()) {
			return String.format("error:更新すべきレコードがありません(Id:%d)。",id);
		}
		
		// Entity 形式に変更
		UserEntity userEntity = userRecord.get();
		
		String returnMessage;
		var password = userForm.getPassword();
		if (password != null && !password.isEmpty()) {
			// パスワード
			returnMessage = new Password().check(password);
			// 強度が OK であれば
			if (returnMessage.equals("OK")) {
				// パスワードをハッシュ化して設定
				userEntity.setPassword(passwordEncoder.encode(password));
			} else {
				// パスワードの強度に問題あれば、
				return returnMessage;
			}
		}
		
		userEntity.setUsername(userForm.getUsername());
		userEntity.setRole(userForm.getRole());
		
		userRepository.save(userEntity);
				
		return "OK";
	}
	
	//
	// ユーザーレコードを削除
	//
	@Override
	public String deleteUser(Integer id) {
		var userRecord = userRepository.findById(id);
		if (userRecord.isPresent()) {
			userRepository.deleteById(id);
			return "OK";
		} else {
			return String.format("削除すべきレコードがありません(Id:%d)。", id);
		}
		
	}

}
