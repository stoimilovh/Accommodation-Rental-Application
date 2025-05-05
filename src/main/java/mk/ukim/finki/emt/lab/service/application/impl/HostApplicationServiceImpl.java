package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.dto.CreateHostDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayHostDTO;
import mk.ukim.finki.emt.lab.projections.HostNameSurnameProjection;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDTO> findAll() {
        return hostService.findAll()
                .stream()
                .map(DisplayHostDTO::from)
                .toList();
    }

    @Override
    public Optional<DisplayHostDTO> findById(Long id) {
        return hostService.findById(id)
                .map(DisplayHostDTO::from);
    }

    @Override
    public Optional<DisplayHostDTO> save(CreateHostDTO hostDTO) {
        Optional<Country> country = countryService.findById(hostDTO.country());

        if (country.isPresent()) {
            return hostService.save(hostDTO.toHost(country.get()))
                    .map(DisplayHostDTO::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDTO> update(Long id, CreateHostDTO hostDTO) {
        Optional<Country> country = countryService.findById(hostDTO.country());

        return hostService.update(id,
                        hostDTO.toHost(country.orElse(null)))
                .map(DisplayHostDTO::from);
    }

    @Override
    public void delete(Long id) {
        hostService.delete(id);
    }

    @Override
    public List<HostNameSurnameProjection> getAllHostNames() {
        return hostService.getAllHostNames();
    }
}