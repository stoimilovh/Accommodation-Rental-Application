package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.dto.CountryDTO;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDTO country) {
        return Optional.of(this.countryRepository.save(new Country(country.getName(), country.getContinent())));
    }

    @Override
    public Optional<Country> update(Long id, CountryDTO countryDTO) {
        return this.countryRepository.findById(id).map(existingCountry -> {
            existingCountry.setName(countryDTO.getName());
            existingCountry.setContinent(countryDTO.getContinent());
            return this.countryRepository.save(existingCountry);
        });
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
