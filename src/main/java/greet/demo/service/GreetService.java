package greet.demo.service;

import java.util.List;

import greet.demo.form.GreetForm;

//
//挨拶サービス
//
public interface GreetService {
	public String saveGreetMessage(GreetForm greetForm);
	public List<String> getLanguages();
	public String sayMessage(String language,String timeframe);
}