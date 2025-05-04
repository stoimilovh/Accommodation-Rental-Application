package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Category;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.dto.CreateAccommodationDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationDTO;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDTO> findAll(String name, Category category, Host host, Integer numRooms, Boolean isRented){
        List<Accommodation> accommodations = accommodationService.findAll(
                name, category, host, numRooms, isRented
        );
        return accommodations.stream().map(DisplayAccommodationDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAccommodationDTO> findById(Long id){
        return accommodationService.findById(id).map(DisplayAccommodationDTO::from);
    }

    @Override
    public Optional<DisplayAccommodationDTO> save(CreateAccommodationDTO accommodation){
        Optional<Host> host = hostService.findById(accommodation.host());
        if(host.isPresent()){
            return accommodationService.save(accommodation.toAccommodation(host.get())).map(DisplayAccommodationDTO::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDTO> update(Long id, CreateAccommodationDTO accommodation){
        Optional<Host> host = hostService.findById(accommodation.host());

        return accommodationService.update(id,
                        accommodation.toAccommodation(host.orElse(null)))
                .map(DisplayAccommodationDTO::from);
    }

    @Override
    public void delete(Long id){
        accommodationService.delete(id);
    }
    @Override
    public Optional<DisplayAccommodationDTO> markAsRented(Long id){
        return accommodationService.markAsRented(id).map(DisplayAccommodationDTO::from);
    }
}
