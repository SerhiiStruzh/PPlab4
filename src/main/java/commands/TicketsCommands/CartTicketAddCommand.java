package commands.TicketsCommands;

import commands.Command;
import logger.LoggerHandler;
import recomendation.RecommendTicketData;
import scanner.ScannerManager;
import travelticket.travelinteraction.TravelTicketInteraction;
import user.User;

public class CartTicketAddCommand implements Command{

	private User user;
	private RecommendTicketData data;
	
	public CartTicketAddCommand(User user, RecommendTicketData data) {
		this.user = user;
		this.data = data;
	}

	public void execute() {
		if(!TravelTicketInteraction.addCartTravelTicket(user, data)) {
			System.out.println("Не вдалося виконати операцію");
			ScannerManager.getScanner().nextLine();
		}
		LoggerHandler.logInfo("Користувач " + user.toString() + " додав до корзини квиток " + data.toString());
	}

}
