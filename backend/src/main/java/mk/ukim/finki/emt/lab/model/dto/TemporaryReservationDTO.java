package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.lab.model.domain.TemporaryReservationStatus;

import java.util.List;

public record TemporaryReservationDTO(
        Long id,
        DisplayUserDTO user,
        List<DisplayAccommodationDTO> accommodations,
        TemporaryReservationStatus reservationStatus
) {

    public static TemporaryReservationDTO from(TemporaryReservation reservation) {
        return new TemporaryReservationDTO(
                reservation.getId(),
                DisplayUserDTO.from(reservation.getUser()),
                DisplayAccommodationDTO.from(reservation.getAccommodations()),
                reservation.getReservationStatus()
        );
    }
}