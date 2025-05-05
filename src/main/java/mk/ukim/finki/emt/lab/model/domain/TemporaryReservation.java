package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TemporaryReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Accommodation> accommodations;

    @Enumerated(EnumType.STRING)
    private TemporaryReservationStatus reservationStatus;

    public TemporaryReservation() {
    }

    public TemporaryReservation(User user) {
        this.user = user;
        this.accommodations = new ArrayList<>();
        this.reservationStatus = TemporaryReservationStatus.CREATED;
    }
}
