package mk.ukim.finki.emt.lab.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservationStatus;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.exceptions.*;
import mk.ukim.finki.emt.lab.repository.TemporaryReservationRepository;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.TemporaryReservationService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationServiceImpl implements TemporaryReservationService {
    private final TemporaryReservationRepository reservationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public TemporaryReservationServiceImpl(TemporaryReservationRepository reservationRepository,
                                  UserService userService,
                                  AccommodationService accommodationService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<TemporaryReservation> listAllReservationsForUser(String username) {
        return reservationRepository.findByUser(userService.findByUsername(username));
    }

    @Override
    public List<Accommodation> listAllAccommodationsInReservation(Long id) {
        if (reservationRepository.findById(id).isEmpty())
            throw new ReservationNotFound(id);

        return reservationRepository.findById(id).get().getAccommodations();
    }

    @Override
    public Optional<TemporaryReservation> getActiveReservation(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(reservationRepository
                .findByUserAndReservationStatus(user, TemporaryReservationStatus.CREATED)
                .orElseGet(() -> reservationRepository.save(new TemporaryReservation(user))));
    }

    @Override
    public Optional<TemporaryReservation> addAccommodationToReservation(String username, Long accommodationId) {
        if (getActiveReservation(username).isPresent()) {
            TemporaryReservation reservation = getActiveReservation(username).get();

            Accommodation accommodation = accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
            if (!reservation.getAccommodations().stream()
                    .filter(a -> a.getId().equals(accommodationId))
                    .toList()
                    .isEmpty())
                throw new AccommodationAlreadyInReservationException(accommodationId, username);

            reservation.getAccommodations().add(accommodation);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TemporaryReservation> removeAccommodationFromReservation(String username, Long accommodationId) {
        if (getActiveReservation(username).isPresent()) {
            TemporaryReservation reservation = getActiveReservation(username).get();

            Accommodation accommodation = accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
            if (reservation.getAccommodations().stream()
                    .filter(a -> a.getId().equals(accommodationId))
                    .toList()
                    .isEmpty())
                throw new AccommodationNotFoundInReservationException(accommodationId, username);

            reservation.getAccommodations().remove(accommodation);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<TemporaryReservation> bookAllAccommodations(String username) {
        if (getActiveReservation(username).isPresent()) {
            TemporaryReservation reservation = getActiveReservation(username).get();

            for (Accommodation accommodation : reservation.getAccommodations()) {
                if (accommodation.isRented())
                    throw new AccommodationUnavailableException(accommodation.getId());

                accommodationService.markAsRented(accommodation.getId());
            }

            reservation.setReservationStatus(TemporaryReservationStatus.CONFIRMED);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TemporaryReservation> cancelReservation(String username) {
        if (getActiveReservation(username).isPresent()) {
            TemporaryReservation reservation = getActiveReservation(username).get();

            reservation.setReservationStatus(TemporaryReservationStatus.CANCELED);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }
}
