package ch.hearc.jeespring.comi.comicarsrental.catalog.model;

import java.util.Date;
import jakarta.persistence.*;


@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    @OneToOne
    @JoinColumn(name = "reservationId")
    private Reservation reservation;

    private double totalCost;
    private Date rentalDate;
    private Date returnDate;
    private Date actualReturnDate;
    private String status;

    // Getters and setters
}
