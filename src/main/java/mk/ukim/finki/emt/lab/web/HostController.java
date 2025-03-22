package mk.ukim.finki.emt.lab.web;


import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.model.dto.HostDTO;
import mk.ukim.finki.emt.lab.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> findAll() {
        return hostService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> findById(@PathVariable Long id) {
        Optional<Host> host = hostService.findById(id);
        return host.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Host> save(@RequestBody HostDTO hostDTO) {
        Optional<Host> createdHost = hostService.save(hostDTO);
        return createdHost.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody HostDTO hostDTO) {
        Optional<Host> updatedHost = hostService.update(id, hostDTO);
        return updatedHost.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hostService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
