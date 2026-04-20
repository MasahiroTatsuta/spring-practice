package greet.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import greet.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	public Optional<UserEntity> findByUserId(String userId);
	public Optional<List<UserEntity>> findAllByOrderByUserId();

}
