package greet.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import greet.demo.form.GreetForm;
import greet.demo.service.GreetService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GreetController {
	
	private final GreetService greetService;
	
	//
	// 挨拶の表示(挨拶要件を入力する画面を表示)
	//
	@GetMapping({"/greet"})
	public ModelAndView greet(ModelAndView modelAndView) {
		List<String> languages=greetService.getLanguages();;
		modelAndView.setViewName("greet");
		modelAndView.addObject("language","日本語");
		modelAndView.addObject("languageOption",languages);
		modelAndView.addObject("timeframe","morning");
		return modelAndView;
	}	
	
	//
	// 挨拶の表示(入力された要件の挨拶を表示)
	//
	@PostMapping("/greet")
	public ModelAndView greetWhat(@RequestParam("language") String language,
			                         @RequestParam("timeframe") String timeframe,
			                         ModelAndView modelAndView) {
		List<String> languages=greetService.getLanguages();
		String message=greetService.sayMessage(language, timeframe);
		modelAndView.setViewName("greet/greet");
		modelAndView.addObject("language",language);
		modelAndView.addObject("languageOption",languages);
		modelAndView.addObject("timeframe",timeframe);
		modelAndView.addObject("message",message);
		return modelAndView;
	}
	
	//
	// 挨拶の登録(登録するデータを入力する画面を表示)
	//
	@GetMapping("/input")
	public String inputGreet(Model model) {
		model.addAttribute("greetForm",new GreetForm());
		return "greet/input";
	}
	
	//
	// 挨拶の登録(入力された挨拶を登録)
	//
	@PostMapping("/input")
	public ModelAndView registerGreet(@ModelAttribute @Validated GreetForm greetForm,
            							BindingResult result,
            							ModelAndView modelAndView) {
		// 入力されたデータのバリデーションエラーチェック
		if (result.hasErrors()) {
			modelAndView.setViewName("greet/input");
			return modelAndView;
		}
		
		// 入力された挨拶文を格納する
		 String returnMessage = greetService.saveGreetMessage( greetForm);
		 if (returnMessage != null) {
			 modelAndView.addObject("error",returnMessage);
		 }

		modelAndView.setViewName("greet/result");
		return modelAndView;
	}
	
	
}
