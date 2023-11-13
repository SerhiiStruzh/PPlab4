package menu;

import java.util.ArrayList;

import commands.RecommendTicketsCommand.RecToPurchaseCommand;
import commands.TicketsCommands.CartTicketAddCommand;
import recomendation.RecommendTicketData;
import scanner.ScannerManager;
import user.User;

public class RecommendedTicketMenu extends Menu{

	private User user;
	private ArrayList<RecommendTicketData> recommends;
	
	public RecommendedTicketMenu(User user, ArrayList<RecommendTicketData> recommends) {
		this.user = user;
		this.recommends = recommends;
	}

	@Override
	public void handleChoice() {
		System.out.println("Виберіть дію:");
		int choice = ScannerManager.getInt();
		while(true) {
			ScannerManager.getScanner().nextLine();
			switch(choice) {
			case 1:
			{
				RecommendTicketData data = recommends.get(getNumberRecommendation(recommends.size()) - 1);
				CartTicketAddCommand addToCart = new CartTicketAddCommand(user, data);
				addToCart.execute();
				MenuManager.pushMenuStack(new RecommendTicketMenu(user));
				return;
			}
			case 2:
				int num = getNumberRecommendation(recommends.size());
				RecommendTicketData data = recommends.get(num - 1);
				CartTicketAddCommand addToCart = new CartTicketAddCommand(user, data);
				addToCart.execute();
				RecToPurchaseCommand buyTicket = new RecToPurchaseCommand(user);
				buyTicket.execute();
				MenuManager.pushMenuStack(new RecommendTicketMenu(user));
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

	private int getNumberRecommendation(int size) {
		System.out.println("Введіть номер рекомендації");
		int num;
		do {
			num = ScannerManager.getInt();
			if(num <= 0 || num > size) {
				System.out.println("Немає такого номера");
			}
		}while(num <= 0 || num > size);
		return num;
	}
	
	@Override
	public void displayMenu() {
		System.out.print("\n\n\n\n");
		System.out.println("Список рекомендованих путівок:");
		for(int i = 0; i < recommends.size(); i++) {
			System.out.println((i + 1) + ": " + recommends.get(i));
		}
		System.out.println("Меню рекомедаційних путівок");
		System.out.println("1. Додати путівку в коризну");
		System.out.println("2. Купити путівку");
		System.out.println("3. В Головне Меню");
		handleChoice();
	}

}
