package ch.hearc.jeespring.comi.comicarsrental.catalog.controller;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehicle(@RequestParam Optional<String> brand, Optional<String> model, Optional<Integer> year, Optional<Double> rentalRate) {
        List<Vehicle> vehicles = vehicleService.searchVehicle(brand.get(),model.get(), year.get(), rentalRate.get().doubleValue());
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @PostMapping("/")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) {
        // Vérification si le véhicule est valide
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
