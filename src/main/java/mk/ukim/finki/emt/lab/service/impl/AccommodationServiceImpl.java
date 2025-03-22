package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.model.dto.AccommodationDTO;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.repository.HostRepository;
import mk.ukim.finki.emt.lab.service.AccommodationService;
import mk.ukim.finki.emt.lab.service.HostService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(AccommodationDTO accommodation) {
        if (accommodation.getCategory() != null &&
                accommodation.getHost() != null && this.hostService.findById(accommodation.getHost()).isPresent()) {
            return Optional.of(
                    this.accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            this.hostService.findById(accommodation.getHost()).get(), accommodation.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDTO accommodation) {
        return this.accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null && !accommodation.getName().isEmpty()) {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getHost() != null && this.hostService.findById(accommodation.getHost()).isPresent()) {
                        existingAccommodation.setHost(this.hostService.findById(accommodation.getHost()).get());
                    }
                    if (accommodation.getNumRooms() != null) {
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    return this.accommodationRepository.save(existingAccommodation);
                });
    }


    @Override
    public void delete(Long id) {
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id).map(accommodation -> {
            accommodation.setRented(true);
            return this.accommodationRepository.save(accommodation);
        });
    }
}
