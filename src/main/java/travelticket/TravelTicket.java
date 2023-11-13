package travelticket;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TravelTicket 
{
	private int ID;
	private int days;
	private String comfortLevel;
	private String start;
	private String end;
	private double distance;
	private double price;
	private Date startDate;

	public TravelTicket(int ID ,int days, String comfortLevel, String start, String end, double distance,
			double price, Date startDate) {
		this.ID = ID;
		this.days = days;
		this.comfortLevel = comfortLevel;
		this.start = start;
		this.end = end;
		this.distance = distance;
		this.price = price;
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "Days: " + days + ", Comfort Level: " + comfortLevel + ", Start: " + start
				+ ", End: " + end + ", Distance: " + distance + ", Price: " + price + ", Start Date: " + dateFormat.format(startDate);
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	public String getComfortLevel() {
		return comfortLevel;
	}
	public void setComfortLevel(String comfortLevel) {
		this.comfortLevel = comfortLevel;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		this.ID = iD;
	}
	
}
