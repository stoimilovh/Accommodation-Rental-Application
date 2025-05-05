package mk.ukim.finki.emt.lab.service.application.impl;


import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationDTO;
import mk.ukim.finki.emt.lab.model.dto.TemporaryReservationDTO;
import mk.ukim.finki.emt.lab.model.exceptions.AccommodationUnavailableException;
import mk.ukim.finki.emt.lab.service.application.TemporaryReservationApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.TemporaryReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {
    private final TemporaryReservationService reservationService;
    private final AccommodationService accommodationService;

    public TemporaryReservationApplicationServiceImpl(TemporaryReservationService reservationService,
                                             AccommodationService accommodationService) {
        this.reservationService = reservationService;
        this.accommodationService = accommodationService;
    }


    @Override
    public List<TemporaryReservationDTO> listAllReservationsForUser(String username) {
        return reservationService.listAllReservationsForUser(username).stream()
                .map(TemporaryReservationDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DisplayAccommodationDTO> listAllAccommodationsInReservation(Long id) {
        return DisplayAccommodationDTO.from(reservationService.listAllAccommodationsInReservation(id));
    }

    @Override
    public Optional<TemporaryReservationDTO> getActiveReservation(String username) {
        return reservationService.getActiveReservation(username).map(TemporaryReservationDTO::from);
    }

    @Override
    public Optional<TemporaryReservationDTO> addAccommodationToReservation(String username, Long accommodationId) {
        var optionalAcc = accommodationService.findById(accommodationId);

        if (optionalAcc.isEmpty() || optionalAcc.get().isRented())
            throw new AccommodationUnavailableException(accommodationId);

        return reservationService.addAccommodationToReservation(username, accommodationId)
                .map(TemporaryReservationDTO::from);
    }

    @Override
    public Optional<TemporaryReservationDTO> removeAccommodationFromReservation(String username, Long accommodationId) {
        if (accommodationService.findById(accommodationId).isPresent()) {
            return reservationService.removeAccommodationFromReservation(username, accommodationId)
                    .map(TemporaryReservationDTO::from);
        }

        return Optional.empty();
    }

    @Override
    public Optional<TemporaryReservationDTO> bookAllAccommodations(String username) {
        return reservationService.bookAllAccommodations(username)
                .map(TemporaryReservationDTO::from);
    }

    @Override
    public Optional<TemporaryReservationDTO> cancelReservation(String username) {
        return reservationService.cancelReservation(username)
                .map(TemporaryReservationDTO::from);
    }
}
