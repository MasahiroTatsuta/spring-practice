package greet.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import greet.demo.entity.GreetEntity;
import lombok.Data;

//
//挨拶テーブル (入力画面フォーム用)
//
@Data
public class GreetForm {
	@NotNull
	@Size(min=1,max=20,message="言語は１文字以上２０文字以下で指定してください")
	private String language;
	@NotNull
	@Size(min=1,max=64,message="朝の挨拶は１文字以上２０文字以下で指定してください")
	private String morning;
	@NotNull
	@Size(min=1,max=64,message="昼の挨拶は１文字以上２０文字以下で指定してください")
	private String noon;
	@NotNull
	@Size(min=1,max=64,message="夕方の挨拶は１文字以上２０文字以下で指定してください")
	private String evening;
	@NotNull
	@Size(min=1,max=64,message="夜の挨拶は１文字以上２０文字以下で指定してください")
	private String night;
	@Size(max=256,message="備考は２５６文字以下で指定してください")
	private String remarks;
	
	public GreetEntity toEntity() {
		GreetEntity entity = new GreetEntity();
		entity.setLanguage(this.language);
		entity.setMorning(this.morning);
		entity.setNoon(this.noon);
		entity.setEvening(this.evening);
		entity.setNight(this.night);
		entity.setRemarks(this.remarks);
		return entity;
	}
	
}
