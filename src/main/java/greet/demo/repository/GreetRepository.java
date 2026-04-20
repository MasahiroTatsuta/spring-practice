package greet.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import greet.demo.entity.GreetEntity;

public interface GreetRepository extends JpaRepository<GreetEntity, Integer> {
	
	// 指定した言語の挨拶を読み出す
	public GreetEntity findByLanguage(String language);
	
	// 登録されている言語の種類を報告する
	@Query("SELECT g.language FROM GreetEntity g")
	List<String> findLanguages();
	
	// 指定した言語が登録されていることを確認する
	public boolean existsByLanguage(String language);

}
