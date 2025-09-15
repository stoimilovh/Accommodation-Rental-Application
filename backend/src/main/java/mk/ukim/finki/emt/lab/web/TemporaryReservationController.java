package mk.ukim.finki.emt.lab.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.dto.TemporaryReservationDTO;
import mk.ukim.finki.emt.lab.model.exceptions.AccommodationUnavailableException;
import mk.ukim.finki.emt.lab.service.application.TemporaryReservationApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@Tag(name = "Reservation API", description = "Endpoints for managing the reservation")
public class TemporaryReservationController {
    private final TemporaryReservationApplicationService reservationApplicationService;

    public TemporaryReservationController(TemporaryReservationApplicationService reservationApplicationService) {
        this.reservationApplicationService = reservationApplicationService;
    }

    @Operation(summary = "Gets all reservations for the user", description = "Retrieves all the reservations for the logged-in user")
    @GetMapping("/all")
    public List<TemporaryReservationDTO> getAllReservations(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return reservationApplicationService.listAllReservationsForUser(user.getUsername());
    }
    @Operation(summary = "Get active reservation", description = "Retrieves the active reservation for the logged-in user")
    @GetMapping
    public ResponseEntity<TemporaryReservationDTO> getActiveReservation(HttpServletRequest request) {
        String username = request.getRemoteUser();

        return reservationApplicationService.getActiveReservation(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add accommodation to reservation", description = "Adds an accommodation to the reservation for the logged-in user")
    @PostMapping("/add-accommodation/{id}")
    public ResponseEntity<?> addToReservation(@PathVariable Long id, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.addAccommodationToReservation(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> new AccommodationUnavailableException(id));
        } catch (AccommodationUnavailableException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Remove accommodation from reservation", description = "Removes an accommodation from the reservation for the logged-in user")
    @PostMapping("/remove-accommodation/{id}")
    public ResponseEntity<?> removeFromReservation(@PathVariable Long id,
                                                   Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.removeAccommodationFromReservation(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> new AccommodationUnavailableException(id));
        } catch (AccommodationUnavailableException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Book reservation", description = "Books all accommodations from the reservation for the logged-in user")
    @PostMapping("/book")
    public ResponseEntity<TemporaryReservationDTO> reserveAllAccommodations(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.bookAllAccommodations(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Cancel reservation", description = "Cancels all accommodations from the reservation for the logged-in user")
    @PostMapping("/cancel")
    public ResponseEntity<TemporaryReservationDTO> cancelReservation(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.cancelReservation(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
