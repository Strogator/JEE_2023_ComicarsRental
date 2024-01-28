package ch.hearc.jeespring.comi.comicarsrental.catalog.service;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Reservation;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByUserId(AppUser AppuserId);
    List<Reservation> getReservationsByVehicleId(Vehicle vehicleId);
    List<Reservation> getReservationsByDateRangeAndStatus(Date startDate, Date endDate, String status);


    void makeReservation(Reservation reservation);

    void cancelReservation(Long reservationId);

    List<Reservation> searchAvailableVehicles(Date startDate, Date endDate);
}