package commands.TicketsCommands;

import java.util.ArrayList;

import commands.Command;
import logger.LoggerHandler;
import menu.MenuManager;
import menu.UserPurchasedTicketsMenu;
import scanner.ScannerManager;
import travelticket.TravelTicket;
import travelticket.travelinteraction.TravelTicketInteraction;
import user.User;

public class PurchasedTicketListCommand implements Command
{
	private User user;
	
	public PurchasedTicketListCommand(User user) {
		this.user = user;
	}

	@Override
	public void execute() {
		ArrayList<TravelTicket> tickets = TravelTicketInteraction.getUserPurchasedTraveTickets(user);
		if(tickets == null || tickets.size() == 0) {
			System.out.println("Немає придбаних квитків!");
		}else {
			for(int i = 0; i < tickets.size(); i++) {
				System.out.println((i + 1) + ": " + tickets.get(i));
			}
		}
		ScannerManager.getScanner().nextLine();
		LoggerHandler.logInfo("Користувач " + user.toString() + " подивився свої придбані квитки");
		MenuManager.pushMenuStack(new UserPurchasedTicketsMenu(user));
	}
}
