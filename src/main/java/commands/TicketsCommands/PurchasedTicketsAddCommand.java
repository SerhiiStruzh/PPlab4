package commands.TicketsCommands;

import commands.Command;
import menu.MainMenu;
import menu.MenuManager;
import travelticket.TravelTicket;
import travelticket.travelinteraction.TravelTicketInteraction;
import user.User;

public class PurchasedTicketsAddCommand implements Command{

	private User user;
	private TravelTicket ticket;
	
	public PurchasedTicketsAddCommand(User user, TravelTicket ticket) {
		this.user = user;
		this.ticket = ticket;
	}

	@Override
	public void execute() {
		if(!TravelTicketInteraction.addUserPurchasedTraveTicket(user, ticket)) {
			System.out.println("Не вдалося додати квиток у список придбаних!");
		}
		MenuManager.pushMenuStack(new MainMenu(user));
	}

}
