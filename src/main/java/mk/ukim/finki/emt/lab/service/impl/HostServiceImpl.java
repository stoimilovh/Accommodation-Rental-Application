package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.model.dto.HostDTO;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.repository.HostRepository;
import mk.ukim.finki.emt.lab.service.CountryService;
import mk.ukim.finki.emt.lab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(HostDTO hostDTO) {
        Optional<Country> countryOptional = this.countryService.findById(hostDTO.getCountry());
        if (countryOptional.isPresent()) {
            Host host = new Host(hostDTO.getName(), hostDTO.getSurname(), countryOptional.get());
            return Optional.of(this.hostRepository.save(host));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Host> update(Long id, HostDTO hostDTO) {
        return this.hostRepository.findById(id).map(host -> {
            if(hostDTO.getName() != null){
                host.setName(hostDTO.getName());
            }
            if(hostDTO.getSurname() != null){
                host.setSurname(hostDTO.getSurname());
            }
            if(hostDTO.getCountry() != null) {
                host.setCountry(countryService.findById(hostDTO.getCountry()).orElse(null));
            }
            return hostRepository.save(host);
        });
    }

    @Override
    public void delete(Long id) {
        this.hostRepository.deleteById(id);
    }
}
