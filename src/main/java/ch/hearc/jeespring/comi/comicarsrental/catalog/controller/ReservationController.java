package ch.hearc.jeespring.comi.comicarsrental.catalog.controller;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Reservation;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.ReservationService;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Controller class for handling reservation-related operations.
 * Author : Alessio Comi.
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private VehicleService vehicleService;

    /**
     * Retrieves a list of available reservations for a given date range.
     *
     * @param startDate The start date of the reservation period.
     * @param endDate   The end date of the reservation period.
     * @return ResponseEntity containing the list of available reservations.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Reservation>> searchAvailableVehicles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<Reservation> availableReservations = reservationService.searchAvailableVehicles(startDate, endDate);
        return ResponseEntity.ok(availableReservations);
    }

    /**
     * Attempts to make a reservation for a vehicle and returns an appropriate response.
     *
     * @param reservation The reservation details.
     * @return ResponseEntity indicating the success or failure of the reservation attempt.
     */
    @PostMapping("/")
    public ResponseEntity<?> makeReservation(@RequestBody Reservation reservation) {
        if (vehicleService.vehicleIsAvailable(reservation.getVehicleId(), reservation.getStartDate(), reservation.getEndDate())) {
            reservationService.makeReservation(reservation);
            return ResponseEntity.ok("Reservation made with success !");
        } else {
            return ResponseEntity.badRequest().body("This vehicle is not available at those dates.");
        }
    }

    /**
     * Cancels a reservation based on the given reservation ID.
     *
     * @param reservationId The ID of the reservation to be canceled.
     * @return ResponseEntity indicating the success of the cancellation.
     */
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Reservation reservationId) {
        reservationService.cancelReservation(reservationId.getReservationId());
        return ResponseEntity.ok("Cancelling reservation successful !");
    }
}
