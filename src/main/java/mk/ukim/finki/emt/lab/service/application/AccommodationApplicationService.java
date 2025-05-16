package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Category;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.dto.CreateAccommodationDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayAccommodationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDTO> findAll(String name, Category category, Host host, Integer numRooms, Boolean isRented);
    Optional<DisplayAccommodationDTO> findById(Long id);
    Optional<DisplayAccommodationDTO> save(CreateAccommodationDTO accommodation);
    Optional<DisplayAccommodationDTO> update(Long id, CreateAccommodationDTO accommodation);
    void delete(Long id);
    Optional<DisplayAccommodationDTO> markAsRented(Long id);
    void refreshMaterializedView();

    Page<DisplayAccommodationDTO> findAll(Pageable pageable);
}