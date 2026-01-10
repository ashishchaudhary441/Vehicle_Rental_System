import java.util.*;
import java.text.*;

class Vehicle {
    int id;
    String name;
    String type;
    double rentPerDay;
    boolean available;

    Vehicle(int id, String name, String type, double rentPerDay) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rentPerDay = rentPerDay;
        this.available = true;
    }
}

class User {
    String username;
    String email;
    String password;

    User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

class Booking {
    String username;
    Vehicle vehicle;
    String startDate;
    String endDate;

    Booking(String username, Vehicle vehicle, String startDate, String endDate) {
        this.username = username;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

public class VehicleRentalSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static User loggedInUser = null;
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        initializeVehicles();
        mainMenu();
    }

    static void mainMenu() {
        while (true) {
            if (loggedInUser == null) {
                System.out.println("\n--- Vehicle Rental System ---");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt(); sc.nextLine();
                switch (choice) {
                    case 1: register(); break;
                    case 2: login(); break;
                    case 3: System.exit(0);
                    default: System.out.println("Invalid option!");
                }
            } else {
                System.out.println("\n--- Welcome " + loggedInUser.username + " ---");
                System.out.println("1. View Vehicles");
                System.out.println("2. Book Vehicle");
                System.out.println("3. View My Bookings");
                System.out.println("4. Calculate Total Price");
                System.out.println("5. Logout");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt(); sc.nextLine();
                switch (choice) {
                    case 1: viewVehicles(); break;
                    case 2: bookVehicle(); break;
                    case 3: viewBookings(); break;
                    case 4: calculateTotalPrice(); break;
                    case 5: loggedInUser = null; break;
                    default: System.out.println("Invalid option!");
                }
            }
        }
    }

    static void initializeVehicles() {
        vehicles.add(new Vehicle(1, "Toyota Corolla", "Car", 2000));
        vehicles.add(new Vehicle(2, "Honda City", "Car", 1800));
        vehicles.add(new Vehicle(3, "Suzuki Bike", "Bike", 500));
        vehicles.add(new Vehicle(4, "Mahindra Bolero", "SUV", 2500));
    }

    static void register() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        for (User u : users) {
            if (u.email.equals(email)) {
                System.out.println("Email already registered!");
                return;
            }
        }
        users.add(new User(username, email, password));
        System.out.println("Registration Successful!");
    }

    static void login() {
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        for (User u : users) {
            if (u.email.equals(email) && u.password.equals(password)) {
                loggedInUser = u;
                System.out.println("Login Successful!");
                return;
            }
        }
        System.out.println("Invalid Email or Password!");
    }

    static void viewVehicles() {
        System.out.println("\n--- Available Vehicles ---");
        for (Vehicle v : vehicles) {
            if (v.available) {
                System.out.println(v.id + ". " + v.name + " | " + v.type + " | Rent per day: " + v.rentPerDay);
            }
        }
    }

    static void bookVehicle() {
        viewVehicles();
        System.out.print("Enter Vehicle ID to book: ");
        int id = sc.nextInt(); sc.nextLine();

        Vehicle selected = null;
        for (Vehicle v : vehicles) {
            if (v.id == id && v.available) {
                selected = v;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Invalid Vehicle ID or Vehicle not available!");
            return;
        }

        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDate = sc.nextLine();
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDate = sc.nextLine();

        if(!isValidDate(startDate) || !isValidDate(endDate)) {
            System.out.println("Invalid date format!");
            return;
        }

        bookings.add(new Booking(loggedInUser.username, selected, startDate, endDate));
        selected.available = false;
        System.out.println("Booking Successful for " + selected.name + "!");
    }

    static void viewBookings() {
        System.out.println("\n--- My Bookings ---");
        boolean found = false;
        for (Booking b : bookings) {
            if (b.username.equals(loggedInUser.username)) {
                System.out.println(b.vehicle.name + " | " + b.startDate + " to " + b.endDate);
                found = true;
            }
        }
        if (!found) System.out.println("No bookings found.");
    }

    static void calculateTotalPrice() {
        double total = 0;
        boolean found = false;
        for (Booking b : bookings) {
            if (b.username.equals(loggedInUser.username)) {
                long days = getDaysBetween(b.startDate, b.endDate);
                total += days * b.vehicle.rentPerDay;
                found = true;
            }
        }
        if (found) {
            System.out.println("Total Price for all your bookings: " + total);
        } else {
            System.out.println("You have no bookings.");
        }
    }

    static boolean isValidDate(String dateStr) {
        try {
            df.setLenient(false);
            df.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static long getDaysBetween(String start, String end) {
        try {
            Date d1 = df.parse(start);
            Date d2 = df.parse(end);
            long diff = d2.getTime() - d1.getTime();
            return (diff / (1000 * 60 * 60 * 24)) + 1; // include start day
        } catch(Exception e) {
            return 0;
        }
    }
}
