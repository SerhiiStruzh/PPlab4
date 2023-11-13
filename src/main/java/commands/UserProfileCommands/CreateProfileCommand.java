package commands.UserProfileCommands;

import commands.Command;
import logger.LoggerHandler;
import menu.MainMenu;
import menu.MenuManager;
import menu.SignInSignUpMenu;
import scanner.ScannerManager;
import user.User;
import user.profileinteraction.UserProfileInteraction;

public class CreateProfileCommand implements Command
{
	private User user;
	private UserProfileInteraction createProfile;
	
	public CreateProfileCommand(User user) 
	{
		this.user = user;
		createProfile = new UserProfileInteraction(this.user);
	}
	
	@Override
	public void execute() {
		if(!createProfile.registerAccount()) {
			System.out.println("Такий акаунт вже інсує!");
			ScannerManager.getScanner().nextLine();
			MenuManager.pushMenuStack(new SignInSignUpMenu());
			LoggerHandler.logInfo("За зареєстрованим емейлом " + user.toString() + " була спроба реєстрації");
			return;
		}
		LoggerHandler.logInfo("Користувач " + user.toString() + " зареєстрував акаунт");
		MenuManager.pushMenuStack(new MainMenu(user));
	}
}
