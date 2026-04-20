package greet.demo.service;

import java.util.List;
import java.util.Optional;

import greet.demo.form.UserForm;

public interface UserService {
	public List<UserForm> getUsers();
	public Optional<UserForm> getUser(Integer id);
	public String insertUser(UserForm userForm);
	public String updateUser(UserForm userForm);
	public String deleteUser(Integer id);
}
