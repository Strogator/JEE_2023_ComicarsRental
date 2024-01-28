package ch.hearc.jeespring.comi.comicarsrental.catalog.repository;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Reservation;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByAppUserId(AppUser appUserId);
    List<Reservation> findByVehicleId(Vehicle vehicleId);
    List<Reservation> findByStartDateBetweenAndStatus(Date startDate, Date endDate, String status);
    List<Reservation> findByEndDateBetweenAndStatus(Date startDate, Date endDate, String status);
    List<Reservation> findByStartDateBeforeAndEndDateAfterAndStatus(Date date, Date date2, String status);

    List<Reservation> findByStartDateBeforeAndEndDateAfter(Date startDate, Date endDate);
}