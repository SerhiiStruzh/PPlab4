package recomendation;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import database.DataBaseOperations;
import scanner.ScannerManager;

public class RecommendTicket 
{
	public static void getTicketRecommendation(ArrayList<RecommendTicketData> dataForTickets) {
		String start = null, end = null, comfLevel = null;
		do {
			start = enterStart();
		}while(!isStartExist(start));
		do {
			end = enterEnd();
		}while(!isEndExist(end));
		do {
			comfLevel = enterComfortLevel();
		}while(!isComfortLevelExist(comfLevel));
		generateRecommendData(start, end, comfLevel, dataForTickets);
	}
	
	private static void generateRecommendData(String start, String end, String comfLevel, ArrayList<RecommendTicketData> dataForTickets){
		String[] comfLevels;
		if(comfLevel.compareTo("") == 0) {
			comfLevels = new String[]{"Luxury", "Premium", "Basic"};
		}else {
			comfLevels = new String[] {comfLevel};
		}
		String sqlQuery = "SELECT * FROM dbo.AvailableRoutes WHERE start_route LIKE ? AND end_route LIKE ?";
		try(ResultSet resultSet = DataBaseOperations.executeSelect(sqlQuery, "%" + start + "%", "%" + end + "%")){
			String s, e;
			double dist;
			while(resultSet.next()) {
				s = resultSet.getString("start_route");
				e = resultSet.getString("end_route");
				dist = resultSet.getDouble("distance");
				for(int i = 0; i < comfLevels.length; i++) {
					dataForTickets.add(new RecommendTicketData(s, e, comfLevels[i], dist));
				}
			}
		}catch(Exception e) {	
			System.out.println("Не вдалося згенерувати рекомендації");
			ScannerManager.getScanner().nextLine();
		}
	}
	
	private static String enterStart() {
		String start;
		System.out.println("Введіть місто звідки хочете відправитися");
		start = ScannerManager.getScanner().nextLine();
		return start;
	}
	
	private static String enterEnd() {
		String end;
		System.out.println("Введіть місто куди хочете відправитися (Натисніть \"Ентер\", щоб пропустити)");
		end = ScannerManager.getScanner().nextLine();
		return end;
	}
	
	private static String enterComfortLevel() {
		String comfLevel;
		System.out.println("Виберіть рівень комфорту (\"Luxury\", \"Premium\",\"Basic\",) (Натисніть \"Ентер\", щоб пропустити)");
		comfLevel = ScannerManager.getScanner().nextLine();
		return comfLevel;
	}
	
	private static boolean isComfortLevelExist(String comfLevel) {
		if(comfLevel.compareTo("") == 0) {
			return true;
		}
		ArrayList<String> comfLevels = new ArrayList<>(Arrays.asList("Luxury", "Premium", "Basic"));
		if(comfLevels.contains(comfLevel)) {
			return true;
		}
		System.out.println("Немає такого типу комфорту в списку доступних!");
		ScannerManager.getScanner().nextLine();
		return false;
	}
	
	private static boolean isEndExist(String end) {
		if(end.compareTo("") == 0) {
			return true;
		}
		String sqlQuery = "SELECT * FROM dbo.AvailableRoutes WHERE end_route LIKE ?";
		try(ResultSet resultSet = DataBaseOperations.executeSelect(sqlQuery, end)){
			if(resultSet.next()) {
				return true;
			}
		}catch(Exception e) {}
		System.out.println("Немає такого міста в списку доступних!");
		ScannerManager.getScanner().nextLine();
		return false;
	}
	
	private static boolean isStartExist(String start) {
		String sqlQuery = "SELECT * FROM dbo.AvailableRoutes WHERE start_route LIKE ?";
		try(ResultSet resultSet = DataBaseOperations.executeSelect(sqlQuery, start)){
			if(resultSet.next()) {
				return true;
			}
		}catch(Exception e) {}
		System.out.println("Немає такого міста в списку доступних!");
		ScannerManager.getScanner().nextLine();
		return false;
	}
}
