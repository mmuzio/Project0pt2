package dealership.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dealership.pojo.User;
import dealership.util.ConnectionFactory;

public class UserDaoPostgres implements UserDao {

	public void createUser(User u) {
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			final String sql = "INSERT INTO Users (UserName, UserType, UserPassWord) VALUES (?, ?, ?)";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setString(1, u.getUsername());
	        
	        preparedStatement.setInt(2, u.getUsertype());
	        
	        preparedStatement.setString(3, u.getPassword());

	        int row = preparedStatement.executeUpdate();
	        
	        System.out.println(row);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}


	}

	public void updateUser(User u) {
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			String sql = "UPDATE Users SET password = ? WHERE username = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, u.getPassword());

	        preparedStatement.setString(2, u.getUsername());

	        int row = preparedStatement.executeUpdate();
	        
	        System.out.println(row);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}

	}
	
	/*

	public void deleteUser(User u) {
	
	}
	
	*/

	public User getUserByUsername(String username) {
		
		User ret = null;
		
		String sql = "Select * from Users where UserName = ?";
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				
				ret = new User();

				ret.setUsername(rs.getString(1));
				
				ret.setUsertype(rs.getInt(2));
				
				ret.setPassword(rs.getString(3));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return ret;
	}

	/*
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

}
