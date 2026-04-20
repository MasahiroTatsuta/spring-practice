package greet.demo.form;

import jakarta.validation.constraints.Size;

import greet.demo.entity.UserEntity;
import lombok.Data;

//
//ユーザー情報テーブル (入力画面フォーム用)
//
@Data
public class UserForm {
	private Integer id;
	@Size(min=8,message="ユーザーIDは8文字以上で指定してください")
	private String userId;
	private String password;
	@Size(min=1,message="ユーザー名を指定してください")
	private String username;
	@Size(min=1,message="役割を指定してください")
	private String role;
	
	public UserEntity toEntity() {
		UserEntity entity=new UserEntity();
		entity.setId(this.id);
		entity.setUserId(this.userId);
		entity.setPassword(this.password);
		entity.setUsername(this.username);
		entity.setRole(this.role);
		return entity;
	}

}
