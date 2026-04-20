package greet.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import greet.demo.entity.UserEntity;

public interface LoginUserRepository extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByUserId(String userId) ;
}
