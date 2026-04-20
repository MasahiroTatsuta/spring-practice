package greet.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import greet.demo.form.UserForm;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false,unique=true,name="user_id")
	private String userId;
	@Column(nullable=false)
	private String password;
	private String username;
	private String role;
	
	public UserForm toForm() {
		UserForm form = new UserForm();
		form.setId(this.id);
		form.setUserId(this.userId);
		form.setPassword(this.password);
		form.setUsername(this.username);
		form.setRole(this.role);
		return form;
	}
}
