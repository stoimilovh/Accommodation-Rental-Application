package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.projections.HostNameSurnameProjection;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(Host country);
    Optional<Host> update(Long id, Host country);
    void delete(Long id);
    List<HostNameSurnameProjection> getAllHostNames();
    void refreshMaterializedView();
}
