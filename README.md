Vehicle Rental System – Java Console Application

A simple Java console-based Vehicle Rental Management System built using OOP principles, DAO pattern, and a clean layered architecture.
It allows you to:

Add vehicles (Car/Bike)

View all vehicles

Rent a vehicle

Track availability

This project runs completely in the terminal (console) and is perfect for beginners learning Java OOP, abstraction, interfaces, and basic project structuring.

Features

✔ Add new vehicles with ID, make, model, rate
✔ Supports Car and Bike types
✔ Rent a vehicle (changes availability)
✔ View all vehicles with availability status
✔ Layered architecture:
   Model → DAO → Service → UI → Main
✔ Clean OOP (Inheritance, Abstract class, Polymorphism, Interface)

How to Run

Save the code in files (or a single file named Main.java)

Compile:

javac Main.java


Run:

java Main

 Project Structure
Main.java
 ├── Vehicle (abstract)
 ├── Car
 ├── Bike
 ├── Customer
 ├── VehicleDAO & VehicleDAOImpl
 ├── RentalService
 ├── AddVehicleUI
 ├── RentVehicleUI
 ├── MainFrame
 └── Main (entry point)
 Example Terminal Output


Example 1: Adding a Vehicle
=== VEHICLE RENTAL SYSTEM ===
1. Add Vehicle
2. View All Vehicles
3. Rent Vehicle
4. Exit
Choose: 1

Enter ID: 101
Make: Honda
Model: City
Rate per day: 2500
Type (1=Car, 2=Bike): 1
Vehicle Added!

Example 2: Adding a Bike
=== VEHICLE RENTAL SYSTEM ===
1. Add Vehicle
2. View All Vehicles
3. Rent Vehicle
4. Exit
Choose: 1

Enter ID: 201
Make: Yamaha
Model: R15
Rate per day: 900
Type (1=Car, 2=Bike): 2
Vehicle Added!

Example 3: Viewing All Vehicles
=== VEHICLE RENTAL SYSTEM ===
1. Add Vehicle
2. View All Vehicles
3. Rent Vehicle
4. Exit
Choose: 2

101 - Honda City (Car) - ₹2500.0 - Available: true
201 - Yamaha R15 (Bike) - ₹900.0 - Available: true

Example 4: Renting a Vehicle
=== VEHICLE RENTAL SYSTEM ===
1. Add Vehicle
2. View All Vehicles
3. Rent Vehicle
4. Exit
Choose: 3

Enter Vehicle ID to rent: 101
Vehicle rented successfully!

Example 5: Viewing Again After Renting
=== VEHICLE RENTAL SYSTEM ===
1. Add Vehicle
2. View All Vehicles
3. Rent Vehicle
4. Exit
Choose: 2

101 - Honda City (Car) - ₹2500.0 - Available: false
201 - Yamaha R15 (Bike) - ₹900.0 - Available: true

Example 6: Renting an Already Rented Vehicle
Enter Vehicle ID to rent: 101
Vehicle not available or not found!

Example 7: Exiting
=== VEHICLE RENTAL SYSTEM ===
1. Add Vehicle
2. View All Vehicles
3. Rent Vehicle
4. Exit
Choose: 4
Exiting...
