package model;

public class Vehicle {
    private int id;
    private String make;
    private String model;
    private String type; // car/bike/van etc.
    private double ratePerDay;
    private boolean available;

    public Vehicle() {}

    public Vehicle(int id, String make, String model, String type, double ratePerDay, boolean available) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.type = type;
        this.ratePerDay = ratePerDay;
        this.available = available;
    }

    public int getId() { return id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public double getRatePerDay() { return ratePerDay; }
    public boolean isAvailable() { return available; }

    public void setId(int id) { this.id = id; }
    public void setMake(String make) { this.make = make; }
    public void setModel(String model) { this.model = model; }
    public void setType(String type) { this.type = type; }
    public void setRatePerDay(double ratePerDay) { this.ratePerDay = ratePerDay; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return id + " | " + make + " " + model + " (" + type + ") - ₹" + ratePerDay + "/day - " + (available ? "Available" : "Rented");
    }
}
