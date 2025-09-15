package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt.lab.model.dto.CreateCountryDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayCountryDTO;
import mk.ukim.finki.emt.lab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(
            summary = "Get all countries",
            description = "Returns a list of all countries including their names and continents."
    )
    @GetMapping
    public List<DisplayCountryDTO> findAll() {
        return countryApplicationService.findAll();
    }

    @Operation(
            summary = "Get a country by ID",
            description = "Returns details of a specific country using its ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDTO> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a new country",
            description = "Adds a new country to the system based on the provided data."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDTO> save(@RequestBody CreateCountryDTO country) {
        return countryApplicationService.save(country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update a country by ID",
            description = "Updates the country with the given ID using the provided details."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDTO> updateCountry(@PathVariable Long id, @RequestBody CreateCountryDTO updatedCountry) {
        return countryApplicationService.update(id, updatedCountry)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a country by ID",
            description = "Removes a country from the system using its ID."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        if (this.countryApplicationService.findById(id).isPresent()) {
            this.countryApplicationService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
