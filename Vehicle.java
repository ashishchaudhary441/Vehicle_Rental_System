import java.util.*;

// ======================= MODEL CLASSES =======================

abstract class Vehicle {
    private int id;
    private String make;
    private String model;
    private double ratePerDay;
    private boolean available;

    public Vehicle(int id, String make, String model, double ratePerDay) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.ratePerDay = ratePerDay;
        this.available = true;
    }

    public int getId() { return id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public double getRatePerDay() { return ratePerDay; }
    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    public abstract String getType();

    @Override
    public String toString() {
        return id + " - " + make + " " + model + " (" + getType() + ") - ₹" + ratePerDay;
    }
}

class Car extends Vehicle {
    public Car(int id, String make, String model, double ratePerDay) {
        super(id, make, model, ratePerDay);
    }
    public String getType() { return "Car"; }
}

class Bike extends Vehicle {
    public Bike(int id, String make, String model, double ratePerDay) {
        super(id, make, model, ratePerDay);
    }
    public String getType() { return "Bike"; }
}

class Customer {
    private int id;
    private String name;
    public Customer(int id, String name) { this.id = id; this.name = name; }
    public int getId() { return id; }
    public String getName() { return name; }
}



// ======================= DAO CLASSES =======================

interface VehicleDAO {
    void addVehicle(Vehicle v);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(int id);
}

class VehicleDAOImpl implements VehicleDAO {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle v) { vehicles.add(v); }

    public List<Vehicle> getAllVehicles() { return vehicles; }

    public Vehicle getVehicleById(int id) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) return v;
        }
        return null;
    }
}



// ======================= SERVICE CLASS =======================

class RentalService {
    private VehicleDAO dao;

    public RentalService(VehicleDAO dao) {
        this.dao = dao;
    }

    public boolean rentVehicle(int id) {
        Vehicle v = dao.getVehicleById(id);
        if (v != null && v.isAvailable()) {
            v.setAvailable(false);
            return true;
        }
        return false;
    }

    public void addVehicle(Vehicle v) {
        dao.addVehicle(v);
    }
}



// ======================= UI CLASSES =======================

class AddVehicleUI {
    private RentalService service;

    public AddVehicleUI(RentalService service) { this.service = service; }

    public void show() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Make: ");
        String make = sc.next();

        System.out.print("Model: ");
        String model = sc.next();

        System.out.print("Rate per day: ");
        double rate = sc.nextDouble();

        System.out.print("Type (1=Car, 2=Bike): ");
        int t = sc.nextInt();

        Vehicle v = (t == 1)
                ? new Car(id, make, model, rate)
                : new Bike(id, make, model, rate);

        service.addVehicle(v);
        System.out.println("Vehicle Added!");
    }
}

class RentVehicleUI {
    private RentalService service;

    public RentVehicleUI(RentalService service) { this.service = service; }

    public void show() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Vehicle ID to rent: ");
        int id = sc.nextInt();

        if (service.rentVehicle(id)) {
            System.out.println("Vehicle rented successfully!");
        } else {
            System.out.println("Vehicle not available or not found!");
        }
    }
}

class MainFrame {

    public MainFrame() {
        VehicleDAOImpl dao = new VehicleDAOImpl();
        RentalService service = new RentalService(dao);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== VEHICLE RENTAL SYSTEM ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int c = sc.nextInt();

            switch (c) {
                case 1 -> new AddVehicleUI(service).show();
                case 2 -> {
                    for (Vehicle v : dao.getAllVehicles()) {
                        System.out.println(v + " - Available: " + v.isAvailable());
                    }
                }
                case 3 -> new RentVehicleUI(service).show();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid Option!");
            }
        }
    }
}



// ======================= MAIN CLASS =======================

public class Main {
    public static void main(String[] args) {
        new MainFrame();
    }
}
