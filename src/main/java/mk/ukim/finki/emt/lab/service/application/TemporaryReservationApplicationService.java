package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationDTO;
import mk.ukim.finki.emt.lab.model.dto.TemporaryReservationDTO;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationApplicationService {
    List<TemporaryReservationDTO> listAllReservationsForUser(String username);

    List<DisplayAccommodationDTO> listAllAccommodationsInReservation(Long id);

    Optional<TemporaryReservationDTO> getActiveReservation(String username);

    Optional<TemporaryReservationDTO> addAccommodationToReservation(String username, Long accommodationId);

    Optional<TemporaryReservationDTO> removeAccommodationFromReservation(String username, Long accommodationId);

    Optional<TemporaryReservationDTO> bookAllAccommodations(String username);

    Optional<TemporaryReservationDTO> cancelReservation(String username);
}
