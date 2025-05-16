package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt.lab.model.domain.Category;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.dto.CreateAccommodationDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationDTO;
import mk.ukim.finki.emt.lab.model.views.AccommodationCountByHostView;
import mk.ukim.finki.emt.lab.repository.AccommodationCountByHostRepository;
import mk.ukim.finki.emt.lab.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@RestController
    @RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;
    private final AccommodationCountByHostRepository accommodationCountByHostRepository;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService, AccommodationCountByHostRepository accommodationCountByHostRepository) {
        this.accommodationApplicationService = accommodationApplicationService;
        this.accommodationCountByHostRepository = accommodationCountByHostRepository;
    }

    @Operation(
            summary = "Get all accommodations",
            description = "Returns a list of all accommodations with their name, category, host ID, and number of rooms."
    )
    @GetMapping
    public List<DisplayAccommodationDTO> findAll(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Category category,
                                                 @RequestParam(required = false) Host host,
                                                 @RequestParam(required = false) Integer numRooms,
                                                 @RequestParam(required = false) Boolean isRented) {

        List<DisplayAccommodationDTO> displayAccommodationDTOS = accommodationApplicationService.findAll(
                name, category, host, numRooms, isRented
        );

        return displayAccommodationDTOS;
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DisplayAccommodationDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(accommodationApplicationService.findAll(pageable));
    }


    @Operation(
            summary = "Get accommodation by ID",
            description = "Fetches details for a single accommodation specified by its ID."
    )
    @GetMapping("/{id}")
    public  ResponseEntity<DisplayAccommodationDTO> findById(@PathVariable Long id) {
        return accommodationApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add new accommodation",
            description = "Creates a new accommodation using the provided data."
    )
    @PostMapping("/add")
    public  ResponseEntity<DisplayAccommodationDTO> save(@RequestBody CreateAccommodationDTO accommodation) {
        return accommodationApplicationService.save(accommodation).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(
            summary = "Delete accommodation by ID",
            description = "Deletes an accommodation from the system using its ID."
    )
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        accommodationApplicationService.delete(id);
    }

    @Operation(
            summary = "Mark accommodation as rented",
            description = "Marks an accommodation as rented using its ID."
    )
    @PatchMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDTO> markAsRented(@PathVariable Long id) {
        return this.accommodationApplicationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/by-host")
    public List<AccommodationCountByHostView> getAccommodationCountByHost() {
        return accommodationCountByHostRepository.findAll();
    }
}
