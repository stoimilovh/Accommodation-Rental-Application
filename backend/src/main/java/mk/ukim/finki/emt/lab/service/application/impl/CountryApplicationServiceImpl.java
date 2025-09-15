package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.model.dto.CreateCountryDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayCountryDTO;
import mk.ukim.finki.emt.lab.service.application.CountryApplicationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDTO> findAll() {
        return DisplayCountryDTO.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDTO> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDTO::from);
    }

    @Override
    public Optional<DisplayCountryDTO> save(CreateCountryDTO country) {
        return countryService.save(country.toCountry()).map(DisplayCountryDTO::from);
    }

    @Override
    public Optional<DisplayCountryDTO> update(Long id, CreateCountryDTO countryDTO) {
        return countryService.update(id, countryDTO.toCountry()).map(DisplayCountryDTO::from);
    }

    @Override
    public void delete(Long id) {
        countryService.delete(id);
    }
}
