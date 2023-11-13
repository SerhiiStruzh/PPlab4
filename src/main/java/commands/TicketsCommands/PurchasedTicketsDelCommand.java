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

public class PurchasedTicketsDelCommand implements Command{

	private User user;
		
	public PurchasedTicketsDelCommand(User user) {
		this.user = user;
	}

	@Override
	public void execute() {
		System.out.println("Введіть номери путівок (Через \"пробіл\"), яка бажаєте видалити,\n(Нечислові символи будуть ігноруватися)");
		ArrayList<Integer> numbersTicketsForDelete = getTravelTicketNumbersForDeletion();
		ArrayList<TravelTicket> tickets = new ArrayList<TravelTicket>(TravelTicketInteraction.getUserPurchasedTraveTickets(user)); 
		for(int number : numbersTicketsForDelete) {
			if(number <= tickets.size()) {
				if(!deleteTicktes(tickets.get(number - 1))) {
					System.out.println("Не вдалося видалити квиток!");
					LoggerHandler.logInfo("Виникла помилка під час видалення квитків користувачем " + user.toString());
					break;
				}
				LoggerHandler.logInfo("Користувач " + user.toString() + " видалив квиток: " + tickets.get(number - 1).toString());
			}
		}
		MenuManager.pushMenuStack(new UserPurchasedTicketsMenu(user));
	}
	
	private boolean deleteTicktes(TravelTicket ticket) {
		return TravelTicketInteraction.deletePurchasedTravelTicket(ticket);
	}
	
	public static ArrayList<Integer> getTravelTicketNumbersForDeletion(){
		ArrayList<Integer> numbers = new ArrayList<Integer>();
        String input = ScannerManager.getScanner().nextLine();

        String[] words = input.split(" ");

        for (String word : words) {
            try {
                int number = Integer.parseInt(word);
                if(number > 0) {
                	 numbers.add(number);
                }
            } catch (NumberFormatException e) {
            }
        }
        return numbers;
	}

}
