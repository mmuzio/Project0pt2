package dealership.dao;

import java.util.List;

import dealership.pojo.Offer;

public interface OfferDao {
	
	public void createOffer(Offer o);
	
	public void acceptOffer(Offer o);
	
	public void rejectOffer(int offerId);
	
	public Offer getOfferById(int id);
	
	public List<Offer> getActiveOffers();
	
	public void viewAllPayments();
	
	public void viewMyPayments(String username);

}
