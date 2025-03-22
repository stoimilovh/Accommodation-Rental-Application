package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.model.dto.HostDTO;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(HostDTO country);
    Optional<Host> update(Long id, HostDTO country);
    void delete(Long id);
}
