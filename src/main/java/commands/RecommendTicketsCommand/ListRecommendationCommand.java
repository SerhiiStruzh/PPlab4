package commands.RecommendTicketsCommand;

import java.util.ArrayList;

import commands.Command;
import logger.LoggerHandler;
import menu.RecommendedTicketMenu;
import menu.MainMenu;
import menu.MenuManager;
import recomendation.RecommendTicket;
import recomendation.RecommendTicketData;
import scanner.ScannerManager;
import user.User;

public class ListRecommendationCommand implements Command {
	
	private User user;
	private ArrayList<RecommendTicketData> recommendedTickets;
	
	public ListRecommendationCommand(User user, ArrayList<RecommendTicketData> recommends) {
		this.user = user;
		this.recommendedTickets = recommends;
	}

	public void execute() {
		RecommendTicket.getTicketRecommendation(recommendedTickets);
		if(recommendedTickets.isEmpty()) {
			System.out.println("Не вдалося згенерувати рекомендації!");
			ScannerManager.getScanner().nextLine();
			LoggerHandler.logInfo("Користувачу " + user.toString() + " не вдалося згенерувати рекомендації");
			MenuManager.pushMenuStack(new MainMenu(user));
		}else {
			for(RecommendTicketData info : recommendedTickets) {
				System.out.println(info);
			}
		}
		LoggerHandler.logInfo("Користувач " + user.toString() + " отримав рекомендації квитків");
		MenuManager.pushMenuStack(new RecommendedTicketMenu(user, recommendedTickets));
	}

}
