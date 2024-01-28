package ch.hearc.jeespring.comi.comicarsrental.catalog.service.implementation;

import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Reservation;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.AppUser;
import ch.hearc.jeespring.comi.comicarsrental.catalog.model.Vehicle;
import ch.hearc.jeespring.comi.comicarsrental.catalog.repository.ReservationRepository;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.ReservationService;
import ch.hearc.jeespring.comi.comicarsrental.catalog.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing Reservation entities.
 * Author : Alessio Comi.
 */

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final VehicleService vehicleService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, VehicleService vehicleService) {
        this.reservationRepository = reservationRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByUserId(AppUser appUserId) {
        return reservationRepository.findByAppUserId(appUserId);
    }

    @Override
    public List<Reservation> getReservationsByVehicleId(Vehicle vehicleId) {
        return reservationRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<Reservation> getReservationsByDateRangeAndStatus(Date startDate, Date endDate, String status) {
        return reservationRepository.findByStartDateBeforeAndEndDateAfterAndStatus(startDate, endDate, status);
    }

    @Override
    public void makeReservation(Reservation reservation) {
        Reservation newReservation = mapDtoToEntity(reservation);
        reservationRepository.save(newReservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }


    @Override
    public List<Reservation> searchAvailableVehicles(Date startDate, Date endDate) {
        List<Reservation> reservations = reservationRepository.findByStartDateBeforeAndEndDateAfter(startDate, endDate);

        // Filtrer les réservations pour obtenir uniquement les véhicules disponibles
        return reservations.stream()
                .filter(reservation -> vehicleService.vehicleIsAvailable(reservation.getVehicleId(), startDate, endDate))
                .collect(Collectors.toList());
    }

    // Méthode utilitaire pour mapper les données de ReservationDto à l'entité Reservation
    private Reservation mapDtoToEntity(Reservation reservation) {
        Reservation newReservation = new Reservation();
        newReservation.setAppUserId(reservation.getAppUserId());
        newReservation.setVehicleId(reservation.getVehicleId());
        newReservation.setStartDate(reservation.getStartDate());
        newReservation.setEndDate(reservation.getEndDate());
        newReservation.setStatus(reservation.getStatus());
        // Assurez-vous d'ajuster cela selon vos besoins spécifiques
        return newReservation;
    }
}