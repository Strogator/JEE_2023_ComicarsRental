package ch.hearc.jeespring.comi.comicarsrental.catalog.service;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> getAllVehicles();
    List<Vehicle> searchVehicle(String brand, String model, Integer manufacturingYear, double rentalRate);

    Vehicle addVehicle(Vehicle vehicle);

    Optional<Vehicle> getVehicleById(Long vehicleId);

    boolean vehicleExists(Long vehicleId);

    void deleteVehicle(Long vehicleId);

    boolean vehicleIsAvailable(Vehicle vehicleId, Date startDate, Date endDate);

    void updateVehicle(Vehicle vehicle);


}