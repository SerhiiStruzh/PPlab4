package menu;

import commands.UserProfileCommands.CreateProfileCommand;
import commands.UserProfileCommands.LoginProfileCommand;
import scanner.ScannerManager;
import user.User;


public class SignInSignUpMenu extends Menu{

	@Override
	public void handleChoice() {
		System.out.println("Виберіть дію:");
		int choice = ScannerManager.getInt();
		User user = new User();
		while(true) {
			ScannerManager.getScanner().nextLine();
			switch(choice) {
			case 1:
				LoginProfileCommand loginProfile = new LoginProfileCommand(user);
				loginProfile.execute();
				return;
			case 2:
				CreateProfileCommand createProfile = new CreateProfileCommand(user);
				createProfile.execute();
				return;
			default: 
				System.out.println("Невірно вибрана дія!");
				choice = ScannerManager.getInt();
			}
		}
	}

	@Override
	public void displayMenu() {
		System.out.print("\n\n\n\n");
		System.out.println("Меню входу:");
        System.out.println("1. Увійти в акаунт");
        System.out.println("2. Створити акаунт");
        handleChoice();
	}
}
