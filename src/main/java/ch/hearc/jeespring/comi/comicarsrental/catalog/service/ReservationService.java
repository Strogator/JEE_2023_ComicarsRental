package ch.hearc.jeespring.comi.comicarsrental.catalog.service;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Reservation;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;

import java.util.Date;
import java.util.List;

/**
 * Service interface for managing Reservation entities.
 * Author : Alessio Comi.
 */
public interface ReservationService {

    /**
     * Retrieves a list of all reservations.
     *
     * @return List of all reservations in the database.
     */
    List<Reservation> getAllReservations();

    /**
     * Retrieves a list of reservations for a specific user.
     *
     * @param appUserId The user ID.
     * @return List of reservations for the specified user.
     */
    List<Reservation> getReservationsByUserId(AppUser appUserId);

    /**
     * Retrieves a list of reservations for a specific vehicle.
     *
     * @param vehicleId The vehicle ID.
     * @return List of reservations for the specified vehicle.
     */
    List<Reservation> getReservationsByVehicleId(Vehicle vehicleId);

    /**
     * Retrieves a list of reservations within a specific date range and with a given status.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @param status    The status of the reservations.
     * @return List of reservations within the specified date range and with the given status.
     */
    List<Reservation> getReservationsByDateRangeAndStatus(Date startDate, Date endDate, String status);

    /**
     * Creates a new reservation.
     *
     * @param reservation The reservation to create.
     */
    void makeReservation(Reservation reservation);

    /**
     * Cancels a reservation.
     *
     * @param reservationId The ID of the reservation to cancel.
     */
    void cancelReservation(Long reservationId);

    /**
     * Searches for available vehicles within a specific date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return List of available reservations within the specified date range.
     */
    List<Reservation> searchAvailableVehicles(Date startDate, Date endDate);
}
