package ch.hearc.jeespring.comi.comicarsrental.catalog.model;

import java.util.Date;

import jakarta.persistence.*;

/**
 * Entity class representing a reservation.
 * Author : Alessio Comi.
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser appUserId;

    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicleId;

    private Date startDate;
    private Date endDate;
    private String status;

    // Getters and setters

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public AppUser getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(AppUser appUserID) {
        this.appUserId = appUserID;
    }

    public Vehicle getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Vehicle vehicle) {
        this.vehicleId = vehicle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
