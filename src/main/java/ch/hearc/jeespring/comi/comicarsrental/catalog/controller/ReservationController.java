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

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/search")
    public ResponseEntity<List<Reservation>> searchAvailableVehicles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<Reservation> availableReservations = reservationService.searchAvailableVehicles(startDate, endDate);
        return ResponseEntity.ok(availableReservations);
    }

    @PostMapping("/")
    public ResponseEntity<?> makeReservation(@RequestBody Reservation reservation) {
        if (vehicleService.vehicleIsAvailable(reservation.getVehicleId(), reservation.getStartDate(), reservation.getEndDate())) {
            reservationService.makeReservation(reservation);
            return ResponseEntity.ok("Reservation made with success !");
        } else {
            return ResponseEntity.badRequest().body("This vehicle is not available at those dates.");
        }
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok("Cancelling reservation successful !");
    }
}
