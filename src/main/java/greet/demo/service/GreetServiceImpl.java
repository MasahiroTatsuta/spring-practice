package greet.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import greet.demo.entity.GreetEntity;
import greet.demo.form.GreetForm;
import greet.demo.repository.GreetRepository;
import lombok.RequiredArgsConstructor;

//
//挨拶サービス
//
@RequiredArgsConstructor
@Service
public class GreetServiceImpl implements GreetService {
	private final GreetRepository greetRepository;
	
	//
	// 新たな言語の挨拶を登録する
	//
	@Override
	public String saveGreetMessage(GreetForm greetForm) {
		
		// 言語の登録状況チェック
		String language = greetForm.getLanguage();
		if (greetRepository.existsByLanguage(language)) {
			return "指定した言語は既に登録されています。";
		}
		
		// 形式の変換
		GreetEntity greetMessage = greetForm.toEntity();
		
		// 挨拶の登録
		try {
			greetRepository.save(greetMessage);
		} catch (Exception e) {
			System.out.println("データベース書き込み時にエラーが発生しました。" + e.toString());
			return "データベース書き込み時にエラーが発生しました";
		}	
		return "OK";
	}
	
	//
	// 登録されている全言語を返す
	//
	@SuppressWarnings("null")
	@Override
	public List<String> getLanguages() {
		
/*		List<GreetMessage> greetMessages=greetRepository.findAll();
		List<String> languages = new ArrayList<String>();
		greetMessages.forEach(message -> languages.add(message.getLanguage()));
*/
		List<String> languages = greetRepository.findLanguages();
		return languages;
	}
	
	//
	// 指定された言語の指定された時間帯の挨拶を返す
	//
	@Override
	public String sayMessage(String language,String timeframe) {
		GreetEntity message=greetRepository.findByLanguage(language);		
		switch (timeframe) {
		case "morning":
			return message.getMorning();
		case "noon":
			return message.getNoon();
		case "evening":
			return message.getEvening();
		case "night":
			return message.getNight();
		default:
			return "登録されていません";
		}
	}

}
