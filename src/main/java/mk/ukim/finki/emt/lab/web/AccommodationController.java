package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.dto.AccommodationDTO;
import mk.ukim.finki.emt.lab.service.AccommodationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accommodation> findAll() {
        return accommodationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Accommodation> findById(@PathVariable Long id) {
        return accommodationService.findById(id);
    }

    @PostMapping("/add")
    public Optional<Accommodation> save(@RequestBody AccommodationDTO accommodation) {
        return accommodationService.save(accommodation);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        accommodationService.delete(id);
    }

    @PatchMapping("/rent/{id}")
    public Optional<Accommodation> markAsRented(@PathVariable Long id) {
        return accommodationService.markAsRented(id);
    }
}
