package greet.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import greet.demo.form.UserForm;
import greet.demo.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService userService;
	
	//
	// ユーザー管理のトップページ
	//
	@GetMapping({"/user", "/user/index"})
	public String index(Model model) {
		List<UserForm> users = userService.getUsers();
		model.addAttribute("users", users);
		return "/user/index";
	}
	
	//
	// 新規登録(ユーザー情報入力画面を表示)
	//
	@GetMapping("/user/create")
	public String create(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "user/create";
	}
	
	//
	// 新規登録(登録情報をデータベースに登録)
	//
	@PostMapping("/user/create")
	public String create(@ModelAttribute @Validated UserForm userForm,
						 BindingResult result,
						 Model model) {
		
		// 入力されたデータのバリデーションエラーチェック
		if (result.hasErrors()) {
			return "/user/create";
		}
		
		// 入力されたユーザー情報を格納する
		String returnMessage = userService.insertUser(userForm);
		if (returnMessage.equals("OK")) {
			// 正常終了した場合 -> ユーザー管理ページへ
			return "redirect:/user/index";
		} else {
			// エラーの場合
			if (returnMessage.startsWith("error")) {
				// "error:"を削除しエラーメッセージを設定
				returnMessage = returnMessage.replaceFirst("error", "");
				model.addAttribute("error", returnMessage);
			} else {
				// パスワードのエラーを設定
				model.addAttribute("passwordError", returnMessage);
			}
			
			return "/user/create";
		}
	}
	
	//
	// 編集(編集するユーザー情報を表示)
	//
	@GetMapping("/user/edit")
	public String edit(@RequestParam("id") Integer id, Model model) {
		// 指定されたレコードを読み取る
		Optional<UserForm> userForm = userService.getUser(id);
		// 読み取れたら表示
		if (userForm.isPresent()) {
			model.addAttribute("userForm", userForm.get());
			return "/user/edit";
		} else {
			model.addAttribute("error", "編集指定された Id(" + id + ")のレコードはありません。");
			return index(model);
		}
	}
	
	//
	// 編集(編集されたユーザー情報をデータベースに反映)
	//
	@PostMapping("/user/edit")
	public String edit(@ModelAttribute @Validated UserForm userForm,
					   BindingResult result,
					   Model model) {
		if (result.hasErrors()) {
			return "/user/edit";
		}
		// 入力されたユーザー情報を格納する
		String returnMessage = userService.updateUser(userForm);
		if (returnMessage.equals("OK")) {
			// 正常終了した場合 -> ユーザー管理ページへ
			return "redirect:/user/index";
		} else {
			// エラーの場合
			if (returnMessage.startsWith("error")) {
				// "error:"を削除しエラーメッセージを設定
				returnMessage = returnMessage.replaceFirst("error", "");
				model.addAttribute("error", returnMessage);
			} else {
				// パスワードのエラーを設定
				model.addAttribute("passwordError", returnMessage);
			}
			
			return "/user/edit";
		}
	}
	
	//
	// 削除(削除するユーザー情報を表示)
	//
	@GetMapping("/user/delete")
	public String delete(@RequestParam("id") Integer id, Model model) {
		// 指定されたレコードを読み取る
		Optional<UserForm> userForm = userService.getUser(id);
		// 読み取れたら表示
		if (userForm.isPresent()) {
			model.addAttribute("userForm", userForm.get());
			return "/user/delete";
		} else {
			model.addAttribute("error", "削除指定された Id(" + id + ")のレコードはありません。");
			return index(model);
		}
	}
	
	//
	// 削除(ユーザー情報をデータベースより削除)
	//
	@PostMapping("/user/delete")
	public String doDelete(@RequestParam("id") Integer id, Model model) {
		// 指定されたユーザー情報を削除する
		String returnMessage = userService.deleteUser(id);
		// 削除出来なかったら
		if (!returnMessage.equals("OK")) {
			model.addAttribute("error", returnMessage);
			return "user/delete";
		}
		// 正常終了した場合 -> ユーザー管理ページへ
		return "redirect:/user/index";
	}
	

}
