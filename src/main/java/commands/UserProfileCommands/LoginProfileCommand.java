package commands.UserProfileCommands;

import commands.Command;
import logger.LoggerHandler;
import menu.MainMenu;
import menu.MenuManager;
import menu.SignInSignUpMenu;
import scanner.ScannerManager;
import user.User;
import user.profileinteraction.UserProfileInteraction;

public class LoginProfileCommand implements Command{

	private User user;
	private UserProfileInteraction loginProfile;
	
	public LoginProfileCommand(User user) 
	{
		this.user = user;
		loginProfile = new UserProfileInteraction(this.user);
	}
	@Override
	public void execute() {
		if(!loginProfile.loginAccount()) {
			System.out.println("Хибно введені дані або користувач незареєстрований!");
			ScannerManager.getScanner().nextLine();
			MenuManager.pushMenuStack(new SignInSignUpMenu());
			LoggerHandler.logInfo("За емейлом " + user.getEmail() + " була невдала спроба входу");
			return;
		}
		LoggerHandler.logInfo("Користувач " + user.toString() + " увійшов в акаунт");
		MenuManager.pushMenuStack(new MainMenu(user));
	}

}
