package ch.hearc.jeespring.comi.comicarsrental.catalog.model;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vehicleId;

    private String brand;
    private String model;
    private Integer manufacturingYear;
    private double rentalRate;


    public Vehicle() {

    }

    public Vehicle(Long vehicleId, String brand, String model, Integer manufacturingYear, double rentalRate) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.rentalRate = rentalRate;
    }

    // Getters and setters
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(Integer manufacturing_year) {
        this.manufacturingYear = manufacturing_year;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }
}
