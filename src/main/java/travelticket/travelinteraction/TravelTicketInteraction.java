package travelticket.travelinteraction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import database.DataBaseOperations;
import recomendation.RecommendTicketData;
import travelticket.TravelTicket;
import user.User;

public class TravelTicketInteraction 
{	
	static public ArrayList<TravelTicket> getUserPurchasedTraveTickets(User user) {
		ArrayList<TravelTicket> tickets = new ArrayList<TravelTicket>();
		String sqlQuery = "SELECT t.ComfLevel,"
				+ " t.ID, "
				+ " t.days_route,"
				+ "	t.distance,"
				+ "	t.end_route,"
				+ "	t.price,"
				+ "	t.start_route, "
				+ " t.startDate "
				+ "FROM dbo.PurchasedPackages AS t "
				+ "JOIN dbo.Users AS u "
				+ "ON u.user_ID = t.user_id "
				+ "WHERE u.user_ID = ?";
		try(ResultSet resultSet = DataBaseOperations.executeSelect(sqlQuery, user.getID())){
			if(resultSet == null) {
				return null;
			}while(resultSet.next()) {
				int ID = resultSet.getInt("ID");
				int days = resultSet.getInt("days_route");
				String ComfortLevel = resultSet.getString("ComfLevel");
				String start = resultSet.getString("start_route");
				String end = resultSet.getString("end_route");
				double distance = resultSet.getDouble("distance");
				double price = resultSet.getDouble("price");
				//Date date = resultSet.getDate("startDate");
				Timestamp timestamp = resultSet.getTimestamp("startDate"); 
		        Date date = new Date(timestamp.getTime());

				tickets.add(new TravelTicket(ID, days, ComfortLevel, start, end, distance, price, date));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}
	
	static public void printCartTickets(User user) {
		String sqlQuery = "SELECT * "
				+ "FROM dbo.CartPackages "
				+ "WHERE user_ID = ?";
		try(ResultSet resultSet = DataBaseOperations.executeSelect(sqlQuery, user.getID())){
			if(resultSet == null) {
				return;
			}
			int i = 1;
			while(resultSet.next()) {
				String start = resultSet.getString("start_route");
				String end = resultSet.getString("end_route");
				String comfLevel = resultSet.getString("ComfLevel");
				double dist = resultSet.getDouble("distance");
				System.out.println(i + ". Start: "+ start + " End: " + end + " Comfort Level: " + comfLevel + " Distance: " + dist);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static public ArrayList<Integer> getUserCartTraveTickets(User user){
		ArrayList<Integer> ticketsID = new ArrayList<Integer>();
		String sqlQuery = "SELECT * "
				+ "FROM dbo.CartPackages "
				+ "WHERE user_ID = ?";
		try(ResultSet resultSet = DataBaseOperations.executeSelect(sqlQuery, user.getID())){
			if(resultSet == null) {
				return null;
			}while(resultSet.next()) {
				int ID = resultSet.getInt("ID");
				ticketsID.add(ID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticketsID;
	}
	
	static public boolean deleteCartTravelTicket(int ID) {
		String sqlQuery = "DELETE FROM dbo.CartPackages WHERE ID = ?";
		return DataBaseOperations.executeUpdate(sqlQuery, ID);
	}
	
	static public boolean deletePurchasedTravelTicket(TravelTicket ticket) {
		String sqlQuery = "DELETE FROM dbo.PurchasedPackages WHERE ID = ?";
		return DataBaseOperations.executeUpdate(sqlQuery, ticket.getID());
	}
	
	static public boolean addCartTravelTicket(User user, RecommendTicketData data) {
		String sqlQuery = "INSERT INTO dbo.CartPackages (user_id, start_route, end_route, ComfLevel, distance)\r\n"
				+ "VALUES (?, ?, ?, ?, ?)";
		return DataBaseOperations.executeUpdate(sqlQuery, user.getID(), data.getStart(), data.getEnd(), data.getComfLevel(), data.getDistance());
	}
	
	static public boolean addUserPurchasedTraveTicket(User user, TravelTicket ticket) 
	{
		String sqlQuery = "INSERT INTO dbo.PurchasedPackages (days_route, ComfLevel, start_route, end_route, distance, price, user_id, startDate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		return DataBaseOperations.executeUpdate(sqlQuery, ticket.getDays(), ticket.getComfortLevel(), 
				ticket.getStart(), ticket.getEnd(), 
				ticket.getDistance(), ticket.getPrice(), user.getID(), ticket.getStartDate());
	}
}
