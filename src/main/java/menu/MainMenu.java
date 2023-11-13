package menu;

import scanner.ScannerManager;
import user.User;

public class MainMenu extends Menu {

	private User user;
	
	public MainMenu(User user) {
		this.user = user;
	}
	
	@Override
	public void handleChoice() {
		System.out.println("Виберіть дію:");
		int choice = ScannerManager.getInt();
		while(true) {
			ScannerManager.getScanner().nextLine();
			switch(choice) {
			case 1:
				MenuManager.pushMenuStack(new UserPurchasedTicketsMenu(user));
				return;
			case 2:
				MenuManager.pushMenuStack(new UserCartTicketsMenu(user));
				return;
			case 3:
				MenuManager.pushMenuStack(new RecommendTicketMenu(user));
				return;
			case 4:
				MenuManager.pushMenuStack(new SignInSignUpMenu());
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
		System.out.println("Головне меню:");
        System.out.println("1. Придбані путівки");
        System.out.println("2. Корзина путівок");
        System.out.println("3. Рекомендація путівок");
        System.out.println("4. Вихід");
        handleChoice();
	}

}
