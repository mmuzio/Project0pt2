package dealership.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dealership.pojo.Car;
import dealership.pojo.Offer;
import dealership.util.ConnectionFactory;

public class OfferDaoPostgres implements OfferDao {
	
	CarDao cDao = new CarDaoPostgres();

	@Override
	public void createOffer(Offer o) {
		
		// Insert the newly created Offer object into the Offers table
		// and set the OfferId of the object
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			String sql = "INSERT INTO Offers (OfferId, Amount, CarId, UserName, Active) VALUES (Default, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setInt(1, o.getAmount());
	        
	        preparedStatement.setInt(2, o.getCarId());
	        
	        preparedStatement.setString(3, o.getUsername());
	        
	        preparedStatement.setBoolean(4, true);

	        preparedStatement.executeUpdate();
	        
	        ResultSet rs = preparedStatement.getGeneratedKeys();
	        
	        while (rs.next()) {
				
				o.setOfferId(rs.getInt(1));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		// Because the OfferId is serial, it is not set in the constructor
		// It is set auto-increment by the database
		// To set the OfferId for our offer object, 
		// use SELECT LASTVAL() to return the id of the newly 
		// inserted record
		/*
		try (Connection conn = ConnectionFactory.getConnection()){
			
			String sql = "SELECT LASTVAL()";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				
				o.setOfferId(rs.getInt(1));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		*/

	}

	// When we accept an offer, we set Active to false
	// This way, we can get payments by:
	// Select amount from offers where active = false
	// We also need to delete all offers on that car
	// We will also have to update the cars table
	
	@Override
	public void acceptOffer(Offer o) {
		
		// First, we set active to false
		
		int offerId;
		
		try {
			
			offerId = o.getOfferId();
			
		} catch (NullPointerException ne) {
			
			System.out.println("No active offer with that ID exists");
			
			return;
			
		}
		
		int carId = o.getCarId();
		
		String carOwner = o.getUsername();
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			String sql = "UPDATE Offers SET Active = ? WHERE OfferId = ? AND Active = true";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        
	        preparedStatement.setBoolean(1, false);
	        
	        preparedStatement.setInt(2, offerId);

	        preparedStatement.executeUpdate();
	        
	        // System.out.println(row);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		// Now, we need to get the carId for this offer
		/*
		
		int carId = -1;
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			String sql = "SELECT CarId FROM Offers WHERE OfferId = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setInt(1, o.getOfferId());
			
			ResultSet rs = preparedStatement.executeQuery(sql);
			
			if (rs.next()) {
				
				carId = rs.getInt(1);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		*/
		
		// Now, we have to set the carOwner in the Cars table
		
		// CarDao cDao = new CarDaoPostgres();
		
		cDao.updateCarOwner(carOwner, carId);
		
		// Now, we delete all other offers for this car
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			String sql = "DELETE FROM Offers WHERE carId = ? AND Active = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setInt(1, carId);
			
			preparedStatement.setBoolean(2, true);

	        preparedStatement.executeUpdate();
	        
	        // System.out.println(row);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		

	}

	// When an employee manually rejects an offer
	// the offer just needs to be deleted
	
	@Override
	public void rejectOffer(int offerId) {
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			final String sql = "DELETE FROM Offers WHERE OfferId = ? AND Active = true";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        
	        preparedStatement.setInt(1, offerId);

	        preparedStatement.executeUpdate();
	        
	        // System.out.println(row);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}

	}

	// Just get the Offer if given its id
	
	@Override
	public Offer getOfferById(int id) {
		
		Offer ret = null;
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			final String sql = "Select * from offers where offerid = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				ret = new Offer();
				
				ret.setOfferId(rs.getInt(1));
				
				ret.setAmount(rs.getInt(2));
				
				ret.setCarId(rs.getInt(3));
				
				ret.setUsername(rs.getString(4));
				
				ret.setActive(rs.getBoolean(5));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return ret;
	}
	
	
	// Get a list of all offers set that are currently active/pending
	// Employees will use this to view offers that can be accepted/rejected
	
	@Override
	public List<Offer> getActiveOffers() {
		
		List<Offer> offersList = new ArrayList<Offer>();
		
		Offer ret = null;
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			final String sql = "Select * from Offers where Active = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setBoolean(1, true);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				ret = new Offer();
				
				ret.setOfferId(rs.getInt(1));
				
				ret.setAmount(rs.getInt(2));
				
				ret.setCarId(rs.getInt(3));
				
				ret.setUsername(rs.getString(4));
				
				ret.setActive(rs.getBoolean(5));
				
				offersList.add(ret);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return offersList;
		
	}
	
	

	@Override
	public void viewAllPayments() {
		
		// int offerId;
		
		int payment;
		
		int carId;
		
		String customer;
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			final String sql = "Select * from Offers where Active = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setBoolean(1, false);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				// offerId = rs.getInt(1);
				
				payment = rs.getInt(2) / 60;
				
				carId = rs.getInt(3);
				
				customer = rs.getString(4);
				
				CarDao cDao = new CarDaoPostgres();
				
				Car car = cDao.getCarById(carId);
				
				System.out.println("Customer " + customer + " makes a payment of $" + payment + " for the following car: \n");
				
				System.out.println(car.toString() + "\n");
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void viewMyPayments(String username) {
		
		int payment;
		
		int carId;
		
		// String username = u.getUsername();
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			final String sql = "Select * from Offers where Active = ? AND UserName = ?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setBoolean(1, false);
	        
	        preparedStatement.setString(2, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			System.out.println("User " + username + " here are your payments: \n\n");
			
			while (rs.next()) {
				
				// offerId = rs.getInt(1);
				
				payment = rs.getInt(2) / 60;
				
				carId = rs.getInt(3);
				
				CarDao cDao = new CarDaoPostgres();
				
				Car car = cDao.getCarById(carId);
				
				System.out.println("You make a payment of " + payment + " for the following car: \n\n");
				
				System.out.println(car.toString() + "\n\n");
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
