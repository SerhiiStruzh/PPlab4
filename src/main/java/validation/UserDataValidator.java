package validation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scanner.ScannerManager;
import user.User;

public class UserDataValidator
{
	public static void enterUserInfoRegistration(User user) {
		Scanner scanner = ScannerManager.getScanner();
		String userName = null, email = null, password = null;
		System.out.println("Введіть ваше ПІБ: ");
		do {
			userName = scanner.nextLine();
		}while(!isNameValid(userName));
		System.out.println("Введіть вашу пошту: ");
		do {
			email = scanner.nextLine();
		}while(!isEmailValid(email));
		System.out.println("Введіть ваш пароль: ");
		do {
			password = scanner.nextLine();
		}while(!isPasswordValid(password));
		user.setName(userName);
		user.setEmail(email);
		user.setPassword(password);
	}
	
	public static void enterUserInfoLogIn(User user) {
		Scanner scanner = ScannerManager.getScanner();
		String email = null, password = null;
		System.out.println("Введіть вашу пошту: ");
		do {
			email = scanner.nextLine();
		}while(!isEmailValid(email));
		System.out.println("Введіть ваш пароль: ");
		do {
			password = scanner.nextLine();
		}while(!isPasswordValid(password));
		user.setEmail(email);
		user.setPassword(password);
	}
	
	private static boolean isPasswordValid(String password) {
		if(password.length() >= 8) {
			return true;
		}
		System.out.println("Пароль повинен містити більше 8 символів");
		return false;
	}
	
	private static boolean isEmailValid(String email) {
		String regexPattern = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()) {
			System.out.println("Невірно введена пошта");
			return false;
		}
		return true;
	}
	
	private static boolean isNameValid(String name) {
		String[] words = name.split(" ");

		for (String word : words) {
		    if (!word.matches("^[a-zA-Zа-щА-ЩЬьЮюЯяЇїІіЄєҐґ]+$")) {
		    	System.out.println("Невірно введені дані(Наявні небуквенні символи)");
		        return false;
		    }
		}
		
		if(words.length == 3) {
			return true;
		}
		System.out.println("Невірно введені дані(Ведіть прізвище, ім'я та побатькові)");
        return false;
	}
}
