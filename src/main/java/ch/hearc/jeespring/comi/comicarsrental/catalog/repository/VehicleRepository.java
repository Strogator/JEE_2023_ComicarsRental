package ch.hearc.jeespring.comi.comicarsrental.catalog.repository;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for accessing Vehicle entities in the database.
 * Author : Alessio Comi.
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    /**
     * Retrieves a list of vehicles based on brand, model, manufacturing year, and rental rate.
     *
     * @param brand            The brand of the vehicle.
     * @param model            The model of the vehicle.
     * @param manufacturingYear The manufacturing year of the vehicle.
     * @param rentalRate       The rental rate of the vehicle.
     * @return List of vehicles matching the specified criteria.
     */
    List<Vehicle> findByBrandAndModelAndManufacturingYearAndRentalRate(String brand, String model, Integer manufacturingYear, double rentalRate);

    /**
     * Retrieves a list of vehicles based on brand and model.
     *
     * @param brand The brand of the vehicle.
     * @param model The model of the vehicle.
     * @return List of vehicles matching the specified brand and model.
     */
    List<Vehicle> findByBrandAndModel(String brand, String model);

    /**
     * Retrieves a list of vehicles based on manufacturing year.
     *
     * @param manufacturingYear The manufacturing year of the vehicle.
     * @return List of vehicles matching the specified manufacturing year.
     */
    List<Vehicle> findByManufacturingYear(Integer manufacturingYear);

    /**
     * Retrieves a list of vehicles based on rental rate.
     *
     * @param rentalRate The rental rate of the vehicle.
     * @return List of vehicles matching the specified rental rate.
     */
    List<Vehicle> findByRentalRate(double rentalRate);
}
