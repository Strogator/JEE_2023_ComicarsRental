package ch.hearc.jeespring.comi.comicarsrental.catalog.repository;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Reservation;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for accessing Reservation entities in the database.
 * Author : Alessio Comi.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Retrieves a list of reservations for a specific user.
     *
     * @param appUserId The ID of the user.
     * @return List of reservations for the specified user.
     */
    List<Reservation> findByAppUserId(AppUser appUserId);

    /**
     * Retrieves a list of reservations for a specific vehicle.
     *
     * @param vehicleId The ID of the vehicle.
     * @return List of reservations for the specified vehicle.
     */
    List<Reservation> findByVehicleId(Vehicle vehicleId);

    /**
     * Retrieves a list of reservations within a specific date range and with a given status.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @param status    The status of the reservations.
     * @return List of reservations within the specified date range and with the given status.
     */
    List<Reservation> findByStartDateBeforeAndEndDateAfterAndStatus(Date startDate, Date endDate, String status);

    /**
     * Retrieves a list of reservations within a specific date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return List of reservations within the specified date range.
     */
    List<Reservation> findByStartDateBeforeAndEndDateAfter(Date startDate, Date endDate);
}
