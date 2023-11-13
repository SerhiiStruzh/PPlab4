package menu;

import commands.TicketsCommands.PurchasedTicketListCommand;
import commands.TicketsCommands.PurchasedTicketsDelCommand;
import scanner.ScannerManager;
import user.User;
import validation.DBInfoRenovate;

public class UserPurchasedTicketsMenu extends Menu {

	private User user;
	
	public UserPurchasedTicketsMenu(User user) {
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
				PurchasedTicketListCommand userTickets = new PurchasedTicketListCommand(user);
				userTickets.execute();
				return;
			case 2:
				PurchasedTicketsDelCommand deleteTickets = new PurchasedTicketsDelCommand(user);
				deleteTickets.execute();
				return;
			case 3:
				MenuManager.pushMenuStack(new MainMenu(user));
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
		DBInfoRenovate.delOldTickets();
		System.out.println("Меню квитків придбаних користувача");
		System.out.println("1. Вивести квитки");
		System.out.println("2. Видалити квитки");
		System.out.println("3. Назад");
		handleChoice();
	}

}
