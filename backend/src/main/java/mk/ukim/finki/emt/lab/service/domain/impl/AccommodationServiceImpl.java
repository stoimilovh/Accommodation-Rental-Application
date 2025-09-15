package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Category;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.repository.AccommodationCountByHostRepository;
import mk.ukim.finki.emt.lab.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.HostService;
import mk.ukim.finki.emt.lab.service.specification.FieldFilterSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service("domainAccommodationService")
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final AccommodationCountByHostRepository accommodationCountByHostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, AccommodationCountByHostRepository accommodationCountByHostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.accommodationCountByHostRepository = accommodationCountByHostRepository;
    }

    @Override
    public List<Accommodation> findAll(String name, Category category, Host host, Integer numRooms, Boolean isRented) {
        Specification<Accommodation> specification = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            specification = specification.and(FieldFilterSpecification.filterContainsText(Accommodation.class, "name", name));
        }

        if (category != null) {
            specification = specification.and(FieldFilterSpecification.filterEqualsV(Accommodation.class, "category", category));
        }

        if (host != null) {
            specification = specification.and(FieldFilterSpecification.filterEquals(Accommodation.class, "host", host.getId()));
        }

        if (numRooms != null) {
            specification = specification.and(FieldFilterSpecification.filterEquals(Accommodation.class, "numRooms", numRooms));
        }

        if(isRented != null) {
            specification = specification.and(FieldFilterSpecification.filterEquals(Accommodation.class, "isRented", isRented));
        }

        return accommodationRepository.findAll(specification);
    }

    @Override
    public Page<Accommodation> findAll(Pageable pageable) {
        return accommodationRepository.findAll(pageable);
    }


    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getCategory() != null &&
                accommodation.getHost() != null && this.hostService.findById(accommodation.getHost().getId()).isPresent()) {
            return Optional.of(
                    this.accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            this.hostService.findById(accommodation.getHost().getId()).get(), accommodation.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return this.accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null && !accommodation.getName().isEmpty()) {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getHost() != null && this.hostService.findById(accommodation.getHost().getId()).isPresent()) {
                        existingAccommodation.setHost(this.hostService.findById(accommodation.getHost().getId()).get());
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

    @Override
    public void refreshMaterializedView(){
        accommodationCountByHostRepository.refreshMaterializedView();
    }
}
