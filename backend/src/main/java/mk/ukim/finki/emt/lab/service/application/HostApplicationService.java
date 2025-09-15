package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.dto.CreateHostDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayHostDTO;
import mk.ukim.finki.emt.lab.projections.HostNameSurnameProjection;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDTO> findAll();
    Optional<DisplayHostDTO> findById(Long id);
    Optional<DisplayHostDTO> save(CreateHostDTO country);
    Optional<DisplayHostDTO> update(Long id, CreateHostDTO country);
    void delete(Long id);
    List<HostNameSurnameProjection> getAllHostNames();
}
