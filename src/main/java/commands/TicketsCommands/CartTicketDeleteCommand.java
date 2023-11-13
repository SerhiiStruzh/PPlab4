package commands.TicketsCommands;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import commands.Command;
import logger.LoggerHandler;
import menu.MenuManager;
import menu.UserCartTicketsMenu;
import scanner.ScannerManager;
import travelticket.travelinteraction.TravelTicketInteraction;
import user.User;

public class CartTicketDeleteCommand implements Command{

	private User user;
	
	public CartTicketDeleteCommand(User user) {
		this.user = user;
	}

	@Override
	public void execute() {
		CopyOnWriteArrayList<Integer> ticketsID = new CopyOnWriteArrayList<Integer>(TravelTicketInteraction.getUserCartTraveTickets(user));
		if(ticketsID.size() == 0) {
			System.out.println("Немає путівок в корзині!");
			ScannerManager.getScanner().nextLine();
		}else {
			System.out.println("Введіть номери путівок (Через \"пробіл\"), яка бажаєте видалити,\n(Нечислові символи будуть ігноруватися)");
			ArrayList<Integer> numbersTicketsForDelete = PurchasedTicketsDelCommand.getTravelTicketNumbersForDeletion();
			for(int number : numbersTicketsForDelete) {
				if(number <= ticketsID.size()) {
					if(!deleteTicktes(ticketsID.get(number - 1))) {
						System.out.println("Не вдалося видалити квиток!");
						break;
					}
				}
			}
		}
		LoggerHandler.logInfo("Користувач " + user.toString() + " видалив квитки з корзини");
		MenuManager.pushMenuStack(new UserCartTicketsMenu(user));
	}
	
	private boolean deleteTicktes(int ticketID) {
		return TravelTicketInteraction.deleteCartTravelTicket(ticketID);
	}
}
