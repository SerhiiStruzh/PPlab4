package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logger.LoggerHandler;

import java.sql.CallableStatement;

public class DataBaseOperations 
{
	static String url = "";
	static Connection connection = null;
	static Statement statement = null;
	
	static public boolean openDBConection() {
        try {
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                statement = connection.createStatement();
            }else {
            	connection.close();
            	LoggerHandler.logCritical("Не вдалося підключитися до БД");
            	return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}
	
	static public boolean executeProcedure(String query, Object... params) {
		try(CallableStatement callableStatement = connection.prepareCall(query)){
			for(int i = 0; i < params.length; i++) {
				callableStatement.setObject(i + 1, params[i]);
			}
			return callableStatement.execute();
		}catch(SQLException e) {return false;}
	}
	
	static public boolean executeUpdate(String query, Object... params) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
        	LoggerHandler.logCritical("Не вдалося виконати запит в БД");
            return false;
        }
    }

	static public ResultSet executeSelect(String query, Object... params) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
        	LoggerHandler.logCritical("Не вдалося виконати запит в БД");
            return null;
        }
    }
	
	static public boolean closeDBConection() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
