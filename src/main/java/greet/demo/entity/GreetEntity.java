package greet.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import greet.demo.form.GreetForm;
import lombok.Data;

//
//挨拶テーブル (データベースアクセス用)
//
@Data
@Entity
@Table(name="greet_message")
public class GreetEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false,unique=true)
	private String language;
	
	private String morning;
	private String noon;
	private String evening;
	private String night;
	private String remarks;
	
	public GreetForm toForm() {
		GreetForm form = new GreetForm();
		form.setLanguage(this.language);
		form.setMorning(this.morning);
		form.setNoon(this.noon);
		form.setEvening(this.evening);
		form.setNight(this.night);
		form.setRemarks(this.remarks);
		return form;
	}

}
