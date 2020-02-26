# Project0 - Car Dealership
 
## About the application

This is a terminal based application that simulates the operation of a car dealership.

There are 3 types of users in the application:

1. Admin

2. Employee

3. Customer

Each user can login and logout of the application. 

Each type of user has specific actions they can take.

The admin has only one unique action:

1. To add employees by giving them a username and password.

Employees have 5 unique actions:

1. To add a car to the lot.

2. To remove a car from the lot.

3. To accept offers that customers have made.

4. To reject offers that customers have made.

5. To view payments that customers have made.

Customers have 4 unique actions:

1. To view cars currently on the lot.

2. To make an offer on a car that is on the lot.

3. To view which cars that they own.

4. To view monthly payments that they make for each car that they own.

## How the application works

On first running the program, the application prompts the user to sign in or register.

Because the program does not currently store any information on program close, it is necessary to login as admin when first running the program.

You must input 1 into the terminal to login.

You may be prompted to type "x" to close the program or type any other key to continue. Just hit ENTER to continue.

The admin username is "admin" and the password is also "admin".

The admin can now create an employee by inputting 1 into the terminal.

You can register with any username and password, but each must be at least 3 characters in length.

After creating an employee, the admin must input 2 into the terminal to logout.

You may now login as the employee that you created. If you input the wrong username and/or password you will be prompted again.

Once you've logged in, you can now begin to add cars to the lot. 

You must input 1 into the terminal to add cars.

You will be prompted to input attributes of the car such as make, model, etc.

This is all that you can do as an employee at this point, because no customers have made any offers or made any payments yet.

Repeat the add car process if you'd like to add multiple cars to the lot.

Now you can log out so that a customer can register.

Once you've logged out you can input 2 to register a customer account.

After registering with a valid username and password, you can now view cars on the lot.

Input 1 to view which cars are currently on the lot.

Please make sure to note the ID of the car that you'd like to make an offer for.

Input 2 to make an offer for a car.

You will be prompted to input the ID of the car you'd like to make an offer for. 

You will be prompted to confirm by typing "yes" into the terminal. Be sure to type "yes", not just "y".

When prompted to input your offer, please be sure to input ONLY NUMBERS. 

Once your offer has been confirmed, you will have to log back in as employee to accept the offer.

Repeat the offer process if you'd like to make multiple offers.

Input 5 to sign out.

If you'd like to make more offers as a new customer, you can input 2 to register a new customer account and make more offers.

To accept/reject offers as an employee, input 1 to sign back in.

Enter your employee username and password.

Once logged in, input 3 to accept or reject offers.

For our purposes, we will start by accepting the offer, but if you'd like to reject offers, please make more offers with any customer account.

You will be prompted with a list of all pending offers. 

Please make sure to not the name of the customer who made the offer, the ID of the car that the offer was made for, and the amount offered.

You will now be prompted to enter this information into the terminal and confirm your acceptance of the offer.

Now that an offer has been accepted, 4 things happen in the system:

1. The car is added to the customer's account. If that customer logs in and selects to view their cars, they will now see their car.

2. The payment is added to the customer's account. The monthly payment is based on a 5 year term with no interest. This is a special limited time offer. If that customer logs in and selects to view their payments, they will now see their payment and the id of the car.

3. The car is removed from the lot. If any customer logins in and selects to view the lot, that car will no longer appear.

4. The payment is added to the employee's account. If the employee selects to view all payments, they will now see the customer's name, the payment amount, and the id of the car.

5. All other offers made for that car are automatically declined. If the employee selects to accept/reject offers, they will no longer see any offers for that car.

This is the entire functionality of the program. You can continue to login and logout as different user types, take various actions, and view the results of each action.





##