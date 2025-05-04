package mk.ukim.finki.emt.lab.web;


import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.dto.CreateHostDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayHostDTO;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostService) {
        this.hostApplicationService = hostService;
    }

    @Operation(
            summary = "Get all hosts",
            description = "Returns a list of all hosts including their names, surnames, and associated country IDs."
    )
    @GetMapping
    public List<DisplayHostDTO> findAll() {
        return hostApplicationService.findAll();
    }

    @Operation(
            summary = "Get a host by ID",
            description = "Returns details of a specific host using their ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDTO> findById(@PathVariable Long id) {
        return hostApplicationService.findById(id).map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a new host",
            description = "Adds a new host to the system using the provided name, surname, and country ID."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDTO> save(@RequestBody CreateHostDTO hostDTO) {
        return hostApplicationService.save(hostDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update an existing host",
            description = "Updates an existing host with the given ID using new details."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDTO> update(@PathVariable Long id, @RequestBody CreateHostDTO hostDTO) {
        return this.hostApplicationService.update(id, hostDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a host",
            description = "Deletes a host from the system using their ID."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hostApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
