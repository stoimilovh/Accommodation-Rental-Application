package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.dto.CreateCountryDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayCountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDTO> findAll();
    Optional<DisplayCountryDTO> findById(Long id);
    Optional<DisplayCountryDTO> save(CreateCountryDTO country);
    Optional<DisplayCountryDTO> update(Long id, CreateCountryDTO country);
    void delete(Long id);
}
