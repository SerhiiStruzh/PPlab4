package commands.RecommendTicketsCommand;

import java.util.ArrayList;
import java.util.Date;

import commands.TicketsCommands.CartToPurchaseCommand;
import scanner.ScannerManager;
import travelticket.TravelTicket;
import travelticket.travelinteraction.TravelTicketInteraction;
import user.User;

public class RecToPurchaseCommand extends CartToPurchaseCommand{

	private User user;
	
	public RecToPurchaseCommand(User user) {
		super(user);
		this.user = user;
	}
	@Override
	public void execute() {
		ArrayList<Integer> ticketsID = TravelTicketInteraction.getUserCartTraveTickets(user);
		if(ticketsID.isEmpty()) {
			System.out.println("В корзині немає путівок!");
			ScannerManager.getScanner().nextLine();
		}else {
			int ticketNumber = ticketsID.size() - 1;
			Date date = generateShippingDate();
			int days = setTravelDays();
			TravelTicket ticket = createTicket(ticketsID, ticketNumber, days, date);
			if(ticket == null) {
				System.out.println("Не вдалося отримати дані з корзини!");
				ScannerManager.getScanner().nextLine();
			}else {
				TravelTicketInteraction.addUserPurchasedTraveTicket(user, ticket);
				ticket.setID(getMaxID());
				TravelTicketInteraction.deleteCartTravelTicket(ticketsID.get(ticketNumber));
			}
		}
	}
}
