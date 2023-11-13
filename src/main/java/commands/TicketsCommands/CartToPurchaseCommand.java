package commands.TicketsCommands;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import commands.Command;
import database.DataBaseOperations;
import logger.LoggerHandler;
import menu.MenuManager;
import menu.UserCartTicketsMenu;
import scanner.ScannerManager;
import travelticket.TicketPriceCalculator;
import travelticket.TravelTicket;
import travelticket.travelinteraction.TravelTicketInteraction;
import user.User;

public class CartToPurchaseCommand implements Command{

	private User user;
	
	public CartToPurchaseCommand(User user) {
		this.user = user;
	}

	@Override
	public void execute() {
		ArrayList<Integer> ticketsID = TravelTicketInteraction.getUserCartTraveTickets(user);
		if(ticketsID.isEmpty()) {
			System.out.println("В корзині немає путівок!");
			ScannerManager.getScanner().nextLine();
		}else {
			int number = getTicketNumber(ticketsID.size());
			Date date = generateShippingDate();
			int days = setTravelDays();
			TravelTicket ticket = createTicket(ticketsID, number - 1, days, date);
			if(ticket == null) {
				System.out.println("Не вдалося отримати дані з корзини!");
				ScannerManager.getScanner().nextLine();
				LoggerHandler.logInfo("Користувачу " + user.toString() + " не вдалося отримати дані з корзини");
			}else {
				TravelTicketInteraction.addUserPurchasedTraveTicket(user, ticket);
				ticket.setID(getMaxID());
				TravelTicketInteraction.deleteCartTravelTicket(ticketsID.get(number - 1));
				LoggerHandler.logInfo("Користувач " + user.toString() + " придбав квиток: " + ticket.toString() + " з корзини");
			}
		}
		MenuManager.pushMenuStack(new UserCartTicketsMenu(user));
	}
	
	protected int getMaxID() {
		String sqlQuery = "SELECT MAX(ID) AS ID FROM dbo.PurchasedPackages";
		int ID = 0;
		try(ResultSet resultSet = DataBaseOperations.executeSelect(sqlQuery)){
			while(resultSet.next()) {
				ID = resultSet.getInt("ID");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ID;
	}
	
	protected TravelTicket createTicket(ArrayList<Integer> ticketsID, int ticketNum, int days, Date date) {
		int ticketID = ticketsID.get(ticketNum);
		String start = null, end = null, comfLevel = null;
		double dist = 0;
		String sqlQuery = "SELECT * FROM dbo.CartPackages WHERE ID = ?";
		try(ResultSet resultSet = DataBaseOperations.executeSelect(sqlQuery, ticketID)){
			while(resultSet.next()) {
				start = resultSet.getString("start_route");
				end = resultSet.getString("end_route");
				comfLevel = resultSet.getString("ComfLevel");
				dist = resultSet.getDouble("distance");
			}
		}catch(Exception e) {
			return null;
		}
		double price = TicketPriceCalculator.calculatePrice(dist, days, comfLevel);
		return new TravelTicket(-1, days, comfLevel, start, end, dist, price, date);
	}
	
	protected int setTravelDays() {
		System.out.println("Введіть кількість днів, на скільки плануєте путівку(Miн. 5 Макс. 30)");
		int days;
		while(true) {
			days = ScannerManager.getInt();
			if(days < 5 || days > 30) {
				System.out.println("Невірно введенні дані!");
			}else {
				return days;
			}
		}
	}
	
	private int getTicketNumber(int size) {
		System.out.println("Введіть номер путівки, яку хочете купити!");
		int number;
		while(true) {
			number = ScannerManager.getInt();
			if(number <= 0 || number > size) {
				System.out.println("В корзині немає путівки з таким номером!");
			}else {
				return number;
			}
		}
	}
	
	protected LocalDate enterDate() {
		int y, m, d;
		System.out.println("Введіть рік на який плануєте подорож");
		y = ScannerManager.getInt();
		System.out.println("Введіть місяць(номер) на який плануєте подорож");
		m = ScannerManager.getInt();
		System.out.println("Введіть день на який плануєте подорож");
		d = ScannerManager.getInt();
		try {
			return LocalDate.of(y, m, d);
		}catch(Exception e) {
			System.out.println("Хибно введені дані!");
			return null;
		}
	}
	
	protected boolean validateDate(LocalDate date) {
		LocalDate maxDate = LocalDate.now();
		LocalDate minDate = LocalDate.now();
		minDate = minDate.plusDays(5);
		maxDate = maxDate.plusYears(2);
		return date.isAfter(minDate) && date.isBefore(maxDate);
	}
	
	protected Date generateShippingDate(){
		LocalDate date;
		Date dateForReturn = null;
		System.out.println("Введіть дату, коли будете відправлятися (Мінімум 5 днів від сьогодні)");
		do {
			date = enterDate();
			if(!(date != null && validateDate(date))){
				System.out.println("Вибрана дата незадовільняє умови!");
			}
		}while(!(date != null && validateDate(date)));
		String time = chooseDepartureTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			dateForReturn = dateFormat.parse(date.toString() + " " + time);
		} catch (ParseException e) {}
		return dateForReturn;
	}

	protected String chooseDepartureTime() {
		String time1 = "10:00:00", time2 = "18:00:00", time3 = "23:00:00";
		System.out.println("Виберіть час відправлення:");
		System.out.println("1. " + time1);
		System.out.println("2. " + time2);
		System.out.println("3. " + time3);
		int choice = ScannerManager.getInt();
		while(true) {
			ScannerManager.getScanner().nextLine();
			switch(choice) {
			case 1:
				return time1;
			case 2:
				return time2;
			case 3:
				return time3;
			default: 
				System.out.println("Невірно вибраний час! Спробуйте ще раз: ");
				choice = ScannerManager.getInt();
			}
		}
	}
	
}
