package ch.hearc.jeespring.comi.comicarsrental.catalog.controller;
import ch.hearc.jeespring.comi.comicarsrental.catalog.controller.VehicleController;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class VehicleControllerTest {

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllVehicles_shouldReturnListOfVehicles() {
        // Arrange
        List<Vehicle> vehicles = Arrays.asList(new Vehicle(), new Vehicle());
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        // Act
        ResponseEntity<List<Vehicle>> response = vehicleController.getAllVehicles();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicles, response.getBody());
    }

    @Test
    void searchVehicle_shouldReturnListOfVehicles() {
        // Arrange
        String brand = "Fiat";
        String model = "Panda";
        Integer year = 2015;
        Double rentalRate = 50.0;
        List<Vehicle> vehicles = Arrays.asList(new Vehicle(), new Vehicle());
        when(vehicleService.searchVehicle(brand, model, year, rentalRate)).thenReturn(vehicles);

        // Act
        ResponseEntity<List<Vehicle>> response = vehicleController.searchVehicle(Optional.of(brand), Optional.of(model), Optional.of(year), Optional.of(rentalRate));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicles, response.getBody());
    }

    @Test
    void addVehicle_shouldReturnCreatedStatus() {
        // Arrange
        String brand = "Fiat";
        String model = "Panda";
        Integer year = 2015;
        Double rentalRate = 50.0;

        Vehicle validVehicle = new Vehicle();
        validVehicle.setBrand(brand);
        validVehicle.setModel(model);
        validVehicle.setManufacturingYear(year);
        validVehicle.setRentalRate(rentalRate);

        // Act
        ResponseEntity<?> response = vehicleController.addVehicle(validVehicle);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(vehicleService, times(1)).addVehicle(validVehicle);
    }

    @Test
    void addVehicle_shouldReturnBadRequestStatusWhenVehicleIsInvalid() {
        // Arrange
        Vehicle invalidVehicle = new Vehicle();

        // Act
        ResponseEntity<?> response = vehicleController.addVehicle(invalidVehicle);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(vehicleService, never()).addVehicle(any());
    }

    @Test
    void updateVehicle_shouldReturnOkStatusWhenVehicleExists() {
        // Arrange
        Long vehicleId = 1L;
        Vehicle updatedVehicle = new Vehicle();
        when(vehicleService.getVehicleById(vehicleId)).thenReturn(Optional.of(new Vehicle()));

        // Act
        ResponseEntity<?> response = vehicleController.updateVehicle(vehicleId, updatedVehicle);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(vehicleService, times(1)).updateVehicle(any());
    }

    @Test
    void updateVehicle_shouldReturnNotFoundStatusWhenVehicleDoesNotExist() {
        // Arrange
        Long nonExistingVehicleId = 999L;
        Vehicle updatedVehicle = new Vehicle();
        when(vehicleService.getVehicleById(nonExistingVehicleId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = vehicleController.updateVehicle(nonExistingVehicleId, updatedVehicle);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vehicleService, never()).updateVehicle(any());
    }

    @Test
    void deleteVehicle_shouldReturnNoContentStatusWhenVehicleExists() {
        // Arrange
        Long vehicleId = 1L;
        when(vehicleService.vehicleExists(vehicleId)).thenReturn(true);

        // Act
        ResponseEntity<?> response = vehicleController.deleteVehicle(vehicleId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(vehicleService, times(1)).deleteVehicle(vehicleId);
    }

    @Test
    void deleteVehicle_shouldReturnNotFoundStatusWhenVehicleDoesNotExist() {
        // Arrange
        Long nonExistingVehicleId = 999L;
        when(vehicleService.vehicleExists(nonExistingVehicleId)).thenReturn(false);

        // Act
        ResponseEntity<?> response = vehicleController.deleteVehicle(nonExistingVehicleId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vehicleService, never()).deleteVehicle(any());
    }
}
