package dealership.driver;


import java.util.Scanner;

import org.apache.log4j.Logger;

import dealership.dao.CarDao;
import dealership.dao.CarDaoPostgres;
import dealership.dao.OfferDao;
import dealership.dao.OfferDaoPostgres;
import dealership.dao.UserDao;
import dealership.dao.UserDaoPostgres;
import dealership.pojo.Car;
import dealership.pojo.Offer;
import dealership.pojo.User;
import dealership.service.UserService;
import dealership.service.UserServiceImpl;

public class Driver {
	
	private static final Logger log = Logger.getLogger(Driver.class);

	static boolean authenticatedUser = false;

	static Scanner input = new Scanner(System.in);
	
	private static String userName;
	
	public static int count = 0;
	
	private static int usertype = 2;
	
	static boolean quit = false;
	
	static UserService uService = new UserServiceImpl();
	
	static UserDao uDao = new UserDaoPostgres();
	
	static OfferDao oDao = new OfferDaoPostgres();
	
	static CarDao cDao = new CarDaoPostgres();
	
	public static void main(String[] args) {
		
		log.info("Program session has started.");
		
		makeSignInSelection();
		
		while((!quit)) {	
			
			if (authenticatedUser) {
				
				System.out.println("Press x to quit, any other key to continue\n");
				
				String selection = input.nextLine();
				
				System.out.println();
				
				if (selection.equals("x")) {
					
					quit = true;
					
					log.info("Program session has ended.");
					
					break;

				}
				
				userSwitch(usertype);
				
				
			} else {
				
				makeSignInSelection();
				
			}
				
		}
		
		input.close();
		
	}
	
	public static void makeSignInSelection() {

		System.out.println("1: Input 1 to sign in OR\n\n2: Input 2 to register\n\n");

		boolean madeValidSelection = false;

		do {

			String selection = input.nextLine();
			
			System.out.println();

			if (selection.equals("1")) {

				System.out.println("Signing in...\n");

				madeValidSelection = true;

				signIn();

			} else if (selection.equals("2")) {

				System.out.println("Registering user...\n");

				madeValidSelection = true;

				registerUser();

			} else {

				System.out.println("Please try again\n");
				
				System.out.println("1: Input 1 to sign in OR\n\n2: Input 2 to register\n\n");

			}
			
		} while (madeValidSelection == false);
		

	}

	public static void signIn() {
		
		authenticatedUser = false;
		
		System.out.println("Enter your username: \n");
		
		String username = input.nextLine();
		
		System.out.println("Enter your password: \n");
		
		String password = input.nextLine();
		
		User u = uService.loginUser(username, password);
		
		if (u != null) {
			
			userName = u.getUsername();
			
			System.out.println("Welcome User " + userName);
			
			authenticatedUser = true;
			
			usertype = u.getUsertype();
			
		} else {
			
			System.out.println("Incorrect username/password combination!!!!!!");
			
		}

	}

	public static void registerUser() {
		
		boolean madeValidSelection = false;
		
		do {
			
			System.out.println("Please enter a username: \n\n");

			String username = input.nextLine();
			
			User u = uDao.getUserByUsername(username);
			
			if (u != null) {
				
				System.out.println("That username is already taken\n");
				
				return;
				
			} else {
				
				System.out.println("Please enter a password: \n\n");

				String password = input.nextLine();
				
				User u2 = new User(2, username, password);
				
				uDao.createUser(u2);
				
				System.out.println("You have successfully registered an account!\n");
				
				usertype = 2;
				
				authenticatedUser = true;
				
				userName = username;
				
				madeValidSelection = true;
				
			}
			
		} while(madeValidSelection == false);

	}

	private static void userSwitch(int userType) {
		
		if (usertype == 1) {
			
			log.info("An employee has logged in.");
			
			employeeSwitch();
			
		} else {
			
			log.info("A customer has logged in.");
			
			customerSwitch();
			
		}
	}

	private static void customerSwitch() {

		boolean madeValidSelection = false;
		
		do {
			
			System.out.println("1: Input 1 to view cars on the lot.\n\n2: Input 2 to make an offer.\n\n3: Input 3 to view your cars.\n\n4: Input 4 to view your payments\n\n5: Input 5 to sign out\n");

			String selection = input.nextLine();
			
			System.out.println();

			if (selection.equals("1")) {

				madeValidSelection = true;
				
				cDao.viewTheLot();

			} else if (selection.equals("2")) {

				madeValidSelection = true;
				
				makeOffer();

			} else if (selection.equals("3")) {

				madeValidSelection = true;
				
				cDao.viewMyCars(userName);
				

			} else if (selection.equals("4")) {

				madeValidSelection = true;
				
				oDao.viewMyPayments(userName);
				

			} else if (selection.equals("5")) {

				madeValidSelection = true;
				
				makeSignInSelection();

			} else {

				System.out.println("Please try again\n");

			}
			
		} while (madeValidSelection == false);
		
	}

	private static void makeOffer() {
		
		int amount; // amount offered
		
		int carId = -1; // initialize to invalid id
		
		System.out.println("Here are the cars available. Please note the id of the car you'd like to make an offer for.\n\n");
		
		cDao.viewTheLot();
		
		System.out.println("Please input the id of the car you'd like to make an offer for.\n\n");

		boolean madeValidSelection = false;
		
		do {
			
			String selection = input.nextLine();
			
			try {
				
				carId = Integer.parseInt(selection);
				
			} catch (NumberFormatException nfe) {
				
				log.error("User has input invalid selection for car ID");
				
				System.out.println("Invalid selection, please enter only numbers!");

				return;
				
			}
			
			try {
				
				Car car = cDao.getCarById(carId);
				
				if (car.getOwner() != null || car.getCarId() == 0) {
					
					log.error("User has input invalid selection for car ID");
					
					System.out.println("Invalid selection, no car with that ID is available!");
					
					return;
					
				}
				
				System.out.println("\nYou have selected the following car:\n\n");
				
				System.out.println(car.toString());
				
			} catch (NullPointerException nfe) {
				
				log.error("User has input invalid selection for car ID");
				
				System.out.println("Invalid selection, please enter only numbers!");

				return;
				
			}
			
			System.out.println("Are you sure you'd like to make an offer for this car?\n\nYes: Input yes to confirm\n\nNo: Input any other key to decline.\n");
			
			String confirmation = input.nextLine().toLowerCase();
			
			if (confirmation.equals("yes")) {
				
				madeValidSelection = true;
				
				System.out.println("\nHow much would you like to offer, in USD?\n");
				
				try {
					
					amount = Integer.parseInt(input.nextLine());
					
				} catch (NumberFormatException nfe) {
					
					log.error("User has input invalid selection for car offer");
					
					System.out.println("Invalid selection, please enter only numbers!");
					
					return;
					
				} 
				
				System.out.printf("\n\nYou have offered $%d for the car.\n\n", amount);
				
				System.out.println("Yes: Input yes to confirm and submit your offer\n\nNo: Input any other key to decline.\n");
				
				String confirmationOrder = input.nextLine().toLowerCase();
				
				if (confirmationOrder.equals("yes")) {
					
					Offer offer = new Offer(amount, carId, userName, true);
					
					oDao.createOffer(offer);
					
					System.out.println("\nYou have successfully submitted your offer. Please login later to see if your offer has been accepted.\n");
					
					madeValidSelection = true;
					
				} else {
					
					System.out.println("\nPlease try again\n");
					
				}
				
			}
			
			
		} while (madeValidSelection == false);
		
	}

	private static void employeeSwitch() {
		
		System.out.println("1: Input 1 to add a car to the lot\n\n2: Input 2 to remove a car from the lot\n\n3: Input 3 to accept or reject offers\n\n4: Input 4 to view payments\n\n5: Input 5 to sign out\n");
		
		boolean madeValidSelection = false;
		
		int price;
		
		int carId;
		
		do {

			String selection = input.nextLine();
			
			System.out.println();

			if (selection.equals("1")) {
				
				madeValidSelection = true;

				System.out.println("What is the brand of the car?\n");
				
				String brand = input.nextLine();
				
				System.out.println();
				
				System.out.println("What is the model of the car?\n");
				
				String model = input.nextLine();
				
				System.out.println();

				System.out.println("What is the year of the car?\n");
				
				String year = input.nextLine();
				
				System.out.println();
				
				System.out.println("What is the color of the car?\n");
				
				String color = input.nextLine();
				
				System.out.println();
				
				System.out.println("What is the condition of the car?\n");
				
				String condition = input.nextLine();
				
				System.out.println();
				
				System.out.println("What is the price of the car?\n\n");
				
				try {
					
					price = Integer.parseInt(input.nextLine());
					
				} catch (NumberFormatException nfe) {
					
					log.error("Employee has input invalid selection for price");
					
					System.out.println("Invalid selection, please enter only numbers!");
					
					return;
					
				}
				
				Car newCar = new Car(brand, model, year, color, condition, price);
				
				cDao.createCar(newCar);

			} else if (selection.equals("2")) {
				
				System.out.println("Please enter the id of the car you would like to remove\n");
				
				try {
					
					carId = Integer.parseInt(input.nextLine());
					
				} catch (NumberFormatException nfe) {

					log.error("User has input invalid selection for car ID");
					
					System.out.println("Invalid selection, please enter only numbers!");
					
					return;
					
				}
				
				System.out.println("\nYou have selected the following car:\n\n");
				
				System.out.println(cDao.getCarById(carId).toString());
				
				System.out.println("\n\nAre you sure you'd like to remove this car? Input yes to confirm, Input any other key to decline.\n");
				
				String confirmation = input.nextLine().toLowerCase();
				
				if (confirmation.equals("yes")) {
					
					madeValidSelection = true;
					
					cDao.removeCar(carId);
					
				} else {
					
					System.out.println("Please try again\n");
					
					return;
					
				}

			} else if (selection.equals("3")) {

				madeValidSelection = true;
				
				offerHandler();

			} else if (selection.equals("4")) {

				madeValidSelection = true;
				
				oDao.viewAllPayments();
				

			} else if (selection.equals("5")) {

				madeValidSelection = true;
				
				makeSignInSelection();

			} else {

				System.out.println("Please try again\n");

			}
			
		} while (madeValidSelection == false);
		
	}

	private static void offerHandler() {
		
		System.out.println("\nHere are the currently pending offers. You will need the offerId to accept or reject the offer.\n");
		
		System.out.println("\nJust go back and view the pending offers to get the needed information. If you input the wrong information you have the option to start over before confirmation of accept or reject.\n");
		
		System.out.println(oDao.getActiveOffers().toString());
		
		System.out.println("\n\nWould you like to accept or reject offers?\n\n");
		
		System.out.println("1: Input 1 to accept offers\n\n2: Input 2 to reject offers\n\n3: Input 3 to see other options.\n\n");
		
		boolean madeValidSelection = false;
		
		do {
			
			String selection = input.nextLine();
			
			System.out.println();
			
			if (selection.equals("1")) {
				
				madeValidSelection = true;

				acceptOffers();

			} else if (selection.equals("2")) {
				
				madeValidSelection = true;
				
				rejectOffers();
				
			} else {
				
				madeValidSelection = true;

				System.out.println("Please try again\n");

			}

		} while (madeValidSelection == false);
		
	}

	private static void rejectOffers() {
		
		int offerId;
		
		System.out.println("\nWhat is the offerId of the offer you'd like to reject?\n");
		
		try {
			
			offerId = Integer.parseInt(input.nextLine());
			
		} catch (NumberFormatException nfe) {

			log.error("User has input invalid selection for car ID");
			
			System.out.println("Invalid selection, please enter only numbers!");
			
			return;
			
		} 
		
		oDao.rejectOffer(offerId);
		
	}
	
	private static void acceptOffers() {
		
		int offerId;
		
		System.out.println("\nWhat is the offerId of the offer you'd like to accept?\n");
		
		try {
			
			offerId = Integer.parseInt(input.nextLine());
			
		} catch (NumberFormatException nfe) {

			log.error("User has input invalid selection for car ID");
			
			System.out.println("Invalid selection, please enter only numbers!");
			
			return;
			
		}
		
		Offer o = oDao.getOfferById(offerId);
		
		oDao.acceptOffer(o);
		
	}
	
}
