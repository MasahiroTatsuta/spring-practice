package greet.demo.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Password {
	//
	// パスワードの強度チェック
	//
	public String check(String password) {
		
		// パスワードなし
		if (password == null || password.isEmpty()) {
			return "パスワードを指定してください。";
		}
		
		// パスワードの長さ不足
		if (password.length() < 10) {
			return "パスワードの長さを10文字以上にしてください。";
		}
		
		// 正規化表現のパスワードチェックパターンとメッセージ
		List<List<String>> checkPatterns = new ArrayList<>();
		checkPatterns.add(new ArrayList<String>(List.of("[A-Z]+","パスワードは英大文字を１文字以上含んでください。")));
		checkPatterns.add(new ArrayList<String>(List.of("[a-z]+","パスワードは英小文字を１文字以上含んでください。")));
		checkPatterns.add(new ArrayList<String>(List.of("[0-9]+","パスワードは数字を１文字以上含んでください。")));
		checkPatterns.add(new ArrayList<String>(List.of("[!#%&@]+","パスワードは記号 !#%&@ を１文字以上含んでください。")));
		checkPatterns.add(new ArrayList<String>(List.of("^[\\x20-\\x7f]*$","パスワードはASCII文字以外を使わないでください。")));
		
		// パスワードチェック
		for (var checkPattern:checkPatterns) {
			// Pattern クラスの find()メソッドでチェック → matches()メソッドを使うと全文字列が一致しないと True にならないので注意のこと
			var ptn = checkPattern.get(0);
			var match = Pattern.compile(ptn).matcher(password).find();
			if(!match) {
				// 強度条件に合わない場合はその条件を通知
				return checkPattern.get(1);
			}
		}
		return "OK";
	}

}
