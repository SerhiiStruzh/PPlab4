package commands.TicketsCommands;

import java.util.ArrayList;

import commands.Command;
import logger.LoggerHandler;
import menu.MenuManager;
import menu.UserCartTicketsMenu;
import scanner.ScannerManager;
import travelticket.travelinteraction.TravelTicketInteraction;
import user.User;

public class CartTicketListCommand implements Command {

	private User user;
	
	public CartTicketListCommand(User user) {
		this.user = user;
	}

	public void execute() {
		ArrayList<Integer> ticketsID = TravelTicketInteraction.getUserCartTraveTickets(user);
		if(ticketsID == null || ticketsID.size() == 0) {
			System.out.println("Немає путівок в списку корзини!");
		}else {
			TravelTicketInteraction.printCartTickets(user);
		}
		ScannerManager.getScanner().nextLine();
		LoggerHandler.logInfo("Користувач " + user.toString() + " вивіт список квитків з корзини");
		MenuManager.pushMenuStack(new UserCartTicketsMenu(user));
	}
}
