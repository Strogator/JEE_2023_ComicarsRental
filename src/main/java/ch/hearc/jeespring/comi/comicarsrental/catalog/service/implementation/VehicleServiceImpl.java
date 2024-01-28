package ch.hearc.jeespring.comi.comicarsrental.catalog.service.implementation;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import ch.hearc.jeespring.comi.comicarsrental.catalog.repository.VehicleRepository;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing Vehicle entities.
 * Author : Alessio Comi.
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> searchVehicle(String brand, String model, Integer year, double rentalRate) {
        if (brand != null && model != null && year != null && rentalRate > 0) {
            return vehicleRepository.findByBrandAndModelAndManufacturingYearAndRentalRate(brand, model, year, rentalRate);
        } else if (brand != null && model != null) {
            return vehicleRepository.findByBrandAndModel(brand, model);
        } else if (year != null) {
            return vehicleRepository.findByManufacturingYear(year);
        } else if (rentalRate > 0) {
            return vehicleRepository.findByRentalRate(rentalRate);
        } else {
            return vehicleRepository.findAll();
        }
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        Vehicle newVehicle = mapDtoToEntity(vehicle);
        return vehicleRepository.save(newVehicle);
    }


    @Override
    public Optional<Vehicle> getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @Override
    public boolean vehicleExists(Long vehicleId) {
        return vehicleRepository.existsById(vehicleId);
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public boolean vehicleIsAvailable(Vehicle vehicleId, Date startDate, Date endDate) {
        return true;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }


    // Méthode utilitaire pour mapper les données de VehicleDto à l'entité Vehicle
    private Vehicle mapDtoToEntity(Vehicle vehicle) {
        Vehicle newVehicle = new Vehicle();
        newVehicle.setVehicleId(vehicle.getVehicleId());
        newVehicle.setBrand(vehicle.getBrand());
        newVehicle.setModel(vehicle.getModel());
        newVehicle.setManufacturingYear(vehicle.getManufacturingYear());
        newVehicle.setRentalRate(vehicle.getRentalRate());
        return newVehicle;
    }

}
