package ch.hearc.jeespring.comi.comicarsrental.catalog.service;

import ch.hearc.jeespring.comi.comicarsrental.catalog.service.implementation.VehicleServiceImpl;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import ch.hearc.jeespring.comi.comicarsrental.catalog.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllVehicles_shouldReturnListOfVehicles() {
        // Arrange
        List<Vehicle> vehicles = Arrays.asList(new Vehicle(), new Vehicle());
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        // Act
        List<Vehicle> result = vehicleService.getAllVehicles();

        // Assert
        assertEquals(vehicles, result);
    }

    @Test
    void searchVehicle_withBrandModelYearAndRentalRate_shouldReturnMatchingVehicles() {
        // Arrange
        String brand = "Toyota";
        String model = "Camry";
        Integer year = 2020;
        double rentalRate = 50.0;
        List<Vehicle> expectedVehicles = Arrays.asList(new Vehicle(), new Vehicle());
        when(vehicleRepository.findByBrandAndModelAndManufacturingYearAndRentalRate(brand, model, year, rentalRate))
                .thenReturn(expectedVehicles);

        // Act
        List<Vehicle> result = vehicleService.searchVehicle(brand, model, year, rentalRate);

        // Assert
        assertEquals(expectedVehicles, result);
    }

    @Test
    void addVehicle_shouldReturnAddedVehicle() {
        // Arrange
        Vehicle inputVehicle = new Vehicle();
        Vehicle savedVehicle = new Vehicle();
        when(vehicleRepository.save(any())).thenReturn(savedVehicle);

        // Act
        Vehicle result = vehicleService.addVehicle(inputVehicle);

        // Assert
        assertEquals(savedVehicle, result);
    }

    @Test
    void getVehicleById_withExistingId_shouldReturnVehicleOptional() {
        // Arrange
        Long vehicleId = 1L;
        Vehicle existingVehicle = new Vehicle();
        when(vehicleRepository.findById(Math.toIntExact(vehicleId))).thenReturn(Optional.of(existingVehicle));

        // Act
        Optional<Vehicle> result = vehicleService.getVehicleById(vehicleId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(existingVehicle, result.get());
    }

    @Test
    void getVehicleById_withNonExistingId_shouldReturnEmptyOptional() {
        // Arrange
        Long nonExistingVehicleId = 999L;
        when(vehicleRepository.findById(Math.toIntExact(nonExistingVehicleId))).thenReturn(Optional.empty());

        // Act
        Optional<Vehicle> result = vehicleService.getVehicleById(nonExistingVehicleId);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void vehicleExists_withExistingId_shouldReturnTrue() {
        // Arrange
        Long vehicleId = 1L;
        when(vehicleRepository.existsById(Math.toIntExact(vehicleId))).thenReturn(true);

        // Act
        boolean result = vehicleService.vehicleExists(vehicleId);

        // Assert
        assertTrue(result);
    }

    @Test
    void vehicleExists_withNonExistingId_shouldReturnFalse() {
        // Arrange
        Long nonExistingVehicleId = 999L;
        when(vehicleRepository.existsById(Math.toIntExact(nonExistingVehicleId))).thenReturn(false);

        // Act
        boolean result = vehicleService.vehicleExists(nonExistingVehicleId);

        // Assert
        assertFalse(result);
    }

    @Test
    void deleteVehicle_withExistingId_shouldInvokeRepositoryDeleteById() {
        // Arrange
        Long vehicleId = 1L;

        // Act
        vehicleService.deleteVehicle(vehicleId);

        // Assert
        verify(vehicleRepository, times(1)).deleteById(Math.toIntExact(vehicleId));
    }

    @Test
    void vehicleIsAvailable_withAvailableVehicle_shouldReturnTrue() {
        // Arrange
        Vehicle availableVehicle = new Vehicle();
        Date startDate = new Date();
        Date endDate = new Date();

        // Act
        boolean result = vehicleService.vehicleIsAvailable(availableVehicle, startDate, endDate);

        // Assert
        assertTrue(result);
    }

    @Test
    void updateVehicle_shouldInvokeRepositorySave() {
        // Arrange
        Vehicle vehicleToUpdate = new Vehicle();

        // Act
        vehicleService.updateVehicle(vehicleToUpdate);

        // Assert
        verify(vehicleRepository, times(1)).save(vehicleToUpdate);
    }
}
