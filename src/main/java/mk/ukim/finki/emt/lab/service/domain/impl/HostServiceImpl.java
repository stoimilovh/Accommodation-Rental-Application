package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.dto.CreateHostDTO;
import mk.ukim.finki.emt.lab.repository.HostRepository;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
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
    public Optional<Host> save(Host hostt) {
        Optional<Country> countryOptional = this.countryService.findById(hostt.getCountry().getId());
        if (countryOptional.isPresent()) {
            Host host = new Host(hostt.getName(), hostt.getSurname(), countryOptional.get());
            return Optional.of(this.hostRepository.save(host));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Host> update(Long id, Host hostt) {
        return this.hostRepository.findById(id).map(host -> {
            if(hostt.getName() != null){
                host.setName(hostt.getName());
            }
            if(hostt.getSurname() != null){
                host.setSurname(hostt.getSurname());
            }
            if(hostt.getCountry() != null) {
                host.setCountry(countryService.findById(hostt.getCountry().getId()).orElse(null));
            }
            return hostRepository.save(host);
        });
    }

    @Override
    public void delete(Long id) {
        this.hostRepository.deleteById(id);
    }
}
