package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservationStatus;
import mk.ukim.finki.emt.lab.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemporaryReservationRepository extends JpaRepository<TemporaryReservation, Long> {
    List<TemporaryReservation> findByUser(User user);

    Optional<TemporaryReservation> findByUserAndReservationStatus(User user, TemporaryReservationStatus reservationStatus);

}
