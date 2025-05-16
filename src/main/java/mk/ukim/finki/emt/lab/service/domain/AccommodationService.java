package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Category;
import mk.ukim.finki.emt.lab.model.domain.Host;
import mk.ukim.finki.emt.lab.model.dto.CreateAccommodationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll(String name, Category category, Host host, Integer numRooms, Boolean isRented);
    Page<Accommodation> findAll(Pageable pageable);
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> save(Accommodation accommodation);
    Optional<Accommodation> update(Long id, Accommodation accommodation);
    void delete(Long id);
    Optional<Accommodation>  markAsRented(Long id);
    void refreshMaterializedView();
}