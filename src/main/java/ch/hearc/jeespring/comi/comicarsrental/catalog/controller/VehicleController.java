package ch.hearc.jeespring.comi.comicarsrental.catalog.controller;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling vehicle-related operations.
 * Author : Alessio Comi.
 */
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * Retrieves a list of all vehicles.
     *
     * @return ResponseEntity containing the list of all vehicles.
     */
    @GetMapping("/")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Searches for vehicles based on optional parameters.
     *
     * @param brand      Optional parameter for filtering by brand.
     * @param model      Optional parameter for filtering by model.
     * @param year       Optional parameter for filtering by manufacturing year.
     * @param rentalRate Optional parameter for filtering by rental rate.
     * @return ResponseEntity containing the list of matching vehicles.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehicle(
            @RequestParam Optional<String> brand,
            @RequestParam Optional<String> model,
            @RequestParam Optional<Integer> year,
            @RequestParam Optional<Double> rentalRate) {
        List<Vehicle> vehicles = vehicleService.searchVehicle(
                brand.orElse(null),
                model.orElse(null),
                year.orElse(null),
                rentalRate.map(Double::doubleValue).orElse(null));
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    /**
     * Adds a new vehicle and returns an appropriate response.
     *
     * @param vehicle The vehicle details.
     * @return ResponseEntity indicating the success or failure of the vehicle addition.
     */
    @PostMapping("/")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) {
        // Check if the vehicle is valid
        if (isValidVehicle(vehicle)) {
            vehicleService.addVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data for vehicle are incorrect.");
        }
    }

    private boolean isValidVehicle(Vehicle vehicle) {
        return vehicle != null &&
                vehicle.getBrand() != null &&
                vehicle.getModel() != null &&
                vehicle.getManufacturingYear() != null &&
                vehicle.getRentalRate() > 0;
    }

    /**
     * Updates an existing vehicle based on the given vehicle ID.
     *
     * @param vehicleId     The ID of the vehicle to be updated.
     * @param updatedVehicle The updated vehicle details.
     * @return ResponseEntity indicating the success or failure of the vehicle update.
     */
    @PutMapping("/{vehicleId}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long vehicleId, @RequestBody Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicleOptional = vehicleService.getVehicleById(vehicleId);

        if (existingVehicleOptional.isPresent()) {
            Vehicle existingVehicle = existingVehicleOptional.get();

            existingVehicle.setBrand(updatedVehicle.getBrand());
            existingVehicle.setModel(updatedVehicle.getModel());
            existingVehicle.setManufacturingYear(updatedVehicle.getManufacturingYear());
            existingVehicle.setRentalRate(updatedVehicle.getRentalRate());

            vehicleService.updateVehicle(existingVehicle);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes a vehicle based on the given vehicle ID.
     *
     * @param vehicleId The ID of the vehicle to be deleted.
     * @return ResponseEntity indicating the success or failure of the vehicle deletion.
     */
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long vehicleId) {
        if (vehicleService.vehicleExists(vehicleId)) {
            vehicleService.deleteVehicle(vehicleId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
