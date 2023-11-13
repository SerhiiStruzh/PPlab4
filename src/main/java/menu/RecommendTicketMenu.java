package menu;

import java.util.ArrayList;

import commands.RecommendTicketsCommand.ListRecommendationCommand;
import recomendation.RecommendTicketData;
import scanner.ScannerManager;
import user.User;

public class RecommendTicketMenu extends Menu {
	private User user;
	private ArrayList<RecommendTicketData> recommends;
	
	public RecommendTicketMenu(User user) {
		this.user = user;
		recommends = new ArrayList<RecommendTicketData>();
	}

	@Override
	public void handleChoice() {
		System.out.println("Виберіть дію:");
		int choice = ScannerManager.getInt();
		while(true) {
			ScannerManager.getScanner().nextLine();
			switch(choice) {
			case 1:
				ListRecommendationCommand recommendaionList = new ListRecommendationCommand(user, recommends);
				recommendaionList.execute();
				return;
			case 2:
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
		System.out.println("Меню рекомендації путівок");
		System.out.println("1. Скласти рекомендацію путівок");
		System.out.println("2. Назад");
		handleChoice();
	}
	
	
}
