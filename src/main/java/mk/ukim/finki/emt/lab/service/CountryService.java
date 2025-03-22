package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.dto.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDTO country);
    Optional<Country> update(Long id, CountryDTO country);
    void delete(Long id);
}
