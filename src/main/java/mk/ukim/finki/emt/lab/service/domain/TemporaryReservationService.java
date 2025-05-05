package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationService {
    List<TemporaryReservation> listAllReservationsForUser(String username);

    List<Accommodation> listAllAccommodationsInReservation(Long id);

    Optional<TemporaryReservation> getActiveReservation(String username);

    Optional<TemporaryReservation> addAccommodationToReservation(String username, Long accommodationId);

    Optional<TemporaryReservation> removeAccommodationFromReservation(String username, Long accommodationId);

    Optional<TemporaryReservation> bookAllAccommodations(String username);

    Optional<TemporaryReservation> cancelReservation(String username);
}
