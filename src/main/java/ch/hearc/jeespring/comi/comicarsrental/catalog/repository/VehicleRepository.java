package ch.hearc.jeespring.comi.comicarsrental.catalog.repository;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository <Vehicle, Integer> {
    List<Vehicle> findByBrandAndModelAndManufacturingYearAndRentalRate(String brand, String model, Integer manufacturingYear, double rentalRate);
    List<Vehicle> findByBrandAndModel(String brand, String model);
    List<Vehicle> findByManufacturingYear(Integer manufacturingYear);
    List<Vehicle> findByRentalRate(double rentalRate);

}
