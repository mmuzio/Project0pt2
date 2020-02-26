package dealership.pojo;

public class Offer {
	
	private int offerId;

	private int amount;
	
	private int carId;

	private String username;
	
	private boolean active;
	
	public Offer() {
		
		super();
		
	}

	public Offer(int amount, int carId, String username, boolean active) {
		
		super();
		
		this.amount = amount;
		
		this.carId = carId;
		
		this.username = username;
		
		this.active = active;
		
	}

	@Override
	public String toString() {
		
		return "Offer \n offerId =" + offerId + "\n amount=" + amount + "\n carId=" + carId + "\n username=" + username + "\n active=" + active + "\n\n";
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + amount;
		result = prime * result + carId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (active != other.active)
			return false;
		if (amount != other.amount)
			return false;
		if (carId != other.carId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	
}
