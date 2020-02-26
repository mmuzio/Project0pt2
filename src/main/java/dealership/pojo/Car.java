package dealership.pojo;

public class Car {

	private String make;
	
	private String model;
	
	private String year;
	
	private String color;
	
	private String condition;
	
	private int price;
	
	private String owner = null;
	
	private int carId;
	
	public Car() {
		
		super();
		
	}
	
	public Car(String make, String model, String year, String color, String condition, int price) {
		
		super();
		
		this.make = make;
		
		this.model = model;
		
		this.year = year;
		
		this.color = color;
		
		this.condition = condition;
		
		this.price = price;
		
	}

	
	@Override
	public String toString() {
		return " carId: " + carId + "\n make: " + make + "\n model: " + model + "\n year: " + year + "\n color: " + color + "\n condition: "
				+ condition + "\n\n The selling price is $" + price + " \n\n";
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}
	
}
