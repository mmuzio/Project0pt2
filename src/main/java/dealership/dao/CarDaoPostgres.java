package dealership.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dealership.pojo.Car;
import dealership.util.ConnectionFactory;

public class CarDaoPostgres implements CarDao {

	@Override
	public void createCar(Car c) {
		
		// Insert the newly created Car object into the Cars table
		// and set the CarId of the object
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			String sql = "INSERT INTO Cars (CarId, Make, Model, MakeYear, Color, CarCondition, Price, CarOwner) VALUES (Default, ?, ?, ?, ?, ?, ?, NULL)";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setString(1, c.getMake());
	        
	        preparedStatement.setString(2, c.getModel());
	        
	        preparedStatement.setString(3, c.getYear());
	        
	        preparedStatement.setString(4, c.getColor());
	        
	        preparedStatement.setString(5, c.getCondition());
	        
	        preparedStatement.setInt(6, c.getPrice());

	        preparedStatement.executeUpdate();
	        
	        ResultSet rs = preparedStatement.getGeneratedKeys();
	        
	        if (rs.next()) {
				
				c.setCarId(rs.getInt(1));
				
			}
	        
	        // System.out.println(row);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		// Because the CarId is serial, it is not set in the constructor
		// It is set auto-increment by the database
		// To set the CarId for our car object, 
		// use SELECT LASTVAL() to return the id of the newly 
		// inserted record
		/*
		try (Connection conn = ConnectionFactory.getConnection()){
			
			String sql = "SELECT LASTVAL()";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				
				c.setCarId(rs.getInt(1));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		*/

	}

	@Override
	public void viewTheLot() {
		
		//int payment;
		
		int carId;
		
		//String customer;
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			final String sql = "Select carId from Cars where CarOwner IS NULL";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				carId = rs.getInt(1);
				
				// CarDao cDao = new CarDaoPostgres();
				
				Car car = null;
				
				car = getCarById(carId);
				
				System.out.println(car.toString());
					
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}

	}

	@Override
	public void viewMyCars(String carOwner) {
		
		int carId;
		
		// String carOwner = u.getUsername();
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			final String sql = "SELECT * from Cars WHERE CarOwner = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        
	        preparedStatement.setString(1, carOwner);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			System.out.println("User " + carOwner + " here are your cars: \n\n");
			
			while (rs.next()) {
				
				carId = rs.getInt(1);
				
				Car car = getCarById(carId);
				
				System.out.println(car.toString() + "\n\n");
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}

	}

	@Override
	public Car getCarById(Integer id) {
		
		Car ret = null;
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			final String sql = "Select * from Cars where CarID = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			ret = new Car();
			
			while (rs.next()) {
				
				ret.setCarId(rs.getInt(1));
				
				ret.setMake(rs.getString(2));
				
				ret.setModel(rs.getString(3));
				
				ret.setYear(rs.getString(4));
				
				ret.setColor(rs.getString(5));
				
				ret.setCondition(rs.getString(6));
				
				ret.setPrice(rs.getInt(7));
				
				ret.setOwner(rs.getString(8));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return ret;
	}

	@Override
	public void updateCarOwner(String carOwner, int carId) {
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			String sql = "UPDATE Cars SET carOwner = ? WHERE carId = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setString(1, carOwner);
	        
	        preparedStatement.setInt(2, carId);

	        preparedStatement.executeUpdate();
	        
	        // System.out.println(row);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void removeCar(int carId) {
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			String sql = "DELETE FROM Cars WHERE CarId = ? AND CarOwner IS NULL";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setInt(1, carId);

	        preparedStatement.executeUpdate();
	        
	        //System.out.println(row);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	/*

	@Override
	public List<Car> getAllCars() {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/

}
