package dealership.dao;

import dealership.pojo.Car;

public interface CarDao {
	
	public void createCar(Car c);
	
	public void viewTheLot();
	
	public void viewMyCars(String carOwner);
	
	public Car getCarById(Integer id);
	
	public void updateCarOwner(String carOwner, int carId);
	
	public void removeCar(int carId);
	
	// public List<Car> getAllCars();

}
