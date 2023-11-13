package travelticket;

public class TicketPriceCalculator {

	final static double COST_PER_KILOMETER = 1.5;
	
	final static double COST_FOR_DAY_LUXURY = 350;
	final static double COST_FOR_DAY_PREMIUM = 200;
	final static double COST_FOR_DAY_BASIC = 100;
	
	public static double calculatePrice(double distance, int days, String comfLevel) {
		double price = 0.0;
		price = price + distance * COST_PER_KILOMETER;
		if(comfLevel.compareTo("Luxury") == 0) {
			price = price + COST_FOR_DAY_LUXURY * days;
		}
		if(comfLevel.compareTo("Premium") == 0) {
			price = price + COST_FOR_DAY_PREMIUM * days;
		}
		if(comfLevel.compareTo("Basic") == 0) {
			price = price + COST_FOR_DAY_BASIC * days;
		}
		return price;
	}
}
