package menu;

import commands.TicketsCommands.CartTicketDeleteCommand;
import commands.TicketsCommands.CartTicketListCommand;
import commands.TicketsCommands.CartToPurchaseCommand;
import scanner.ScannerManager;
import user.User;

public class UserCartTicketsMenu extends Menu{

	private User user;
	
	public UserCartTicketsMenu(User user) {
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
				CartTicketListCommand userTickets = new CartTicketListCommand(user);
				userTickets.execute();
				return;
			case 2:
				CartTicketDeleteCommand deleteTickets = new CartTicketDeleteCommand(user);
				deleteTickets.execute();
				return;
			case 3:
				CartToPurchaseCommand addTicket = new CartToPurchaseCommand(user);
				addTicket.execute();
				return;
			case 4:
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
		System.out.println("Меню корзини путівок");
		System.out.println("1. Вивести путівки");
		System.out.println("2. Видалити путівки");
		System.out.println("3. Купити путівку з корзини");
		System.out.println("4. Назад");
		handleChoice();
	}

}
