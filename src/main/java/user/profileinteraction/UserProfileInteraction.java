package user.profileinteraction;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DataBaseOperations;
import user.User;
import validation.UserDataValidator;

public class UserProfileInteraction  
{
	private User user;
	
	public UserProfileInteraction(User user) 
	{
		this.user = user;
	}
	
	public boolean registerAccount() {
		UserDataValidator.enterUserInfoRegistration(user);
		if(!isUserRegistered()) {
			return insertUserToDatabase();
		}
		return false;
	}
	
	private boolean insertUserToDatabase() {
		String[] names = user.getName().split(" ");
		if(DataBaseOperations.executeUpdate("INSERT INTO dbo.Users (first_name, middle_name, last_name, email, password)"
		+ " VALUES(?, ?, ?, ?, ?)", names[0], names[1], names[2], user.getEmail(), user.getPassword())) {
			user.setID(retrieveUserIDByEmail());
			return true;
		}
		return false;
	}
	
	public boolean loginAccount() {
        UserDataValidator.enterUserInfoLogIn(user);
        if (isUserRegistered() && isCorrectPassword()) {
            user.setName(retrieveUserNameByEmail());
            user.setID(retrieveUserIDByEmail());
            return true;
        }
        return false;
    }
	
	private int retrieveUserIDByEmail() {
		try(ResultSet resultSet = DataBaseOperations.executeSelect("SELECT user_ID FROM dbo.users WHERE email LIKE ?", 
													user.getEmail())){
			if(resultSet == null) {
				return 0;
			}while(resultSet.next()) {
				return resultSet.getInt("user_ID");
			}
		}catch(SQLException e) {}
		return 0;
	}
	
	private String retrieveUserNameByEmail() {
		try (ResultSet resultSet = DataBaseOperations.executeSelect("SELECT * FROM dbo.users WHERE email LIKE ?", 
													user.getEmail())) {
	        if (resultSet == null) {
	            return null;
	        }while (resultSet.next()) {
	        	String first_name = resultSet.getString("first_name");
	            String middle_name = resultSet.getString("middle_name");
	            String last_name = resultSet.getString("last_name");
	            return first_name + " " + middle_name + " " + last_name;
	        }
	    } catch (SQLException e) { }
		return null;
	}
	
	private boolean isCorrectPassword() {
		try (ResultSet resultSet = DataBaseOperations.executeSelect("SELECT * FROM dbo.users WHERE email LIKE ?", 
													user.getEmail())) {
	        if (resultSet == null) {
	            return false;
	        }
	        while (resultSet.next()) {
	            String password = resultSet.getString("password");
	            return user.getPassword().compareTo(password) == 0;
	        }
	    } catch (SQLException e) { }
	    return false;
	}
	
	private boolean isUserRegistered() {
	    try (ResultSet resultSet = DataBaseOperations.executeSelect("SELECT * FROM dbo.users WHERE email LIKE ?", user.getEmail())) {
	        if (resultSet == null) {
	            return true;
	        }
	        while (resultSet.next()) {
	            String email = resultSet.getString("email");
	            if (user.getEmail().compareTo(email) == 0) {
	                return true;
	            }
	        }
	    } catch (SQLException e) {  }
	    return false;
	}
}
