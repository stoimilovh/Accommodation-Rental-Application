package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.dto.AccommodationDTO;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> save(AccommodationDTO accommodation);
    Optional<Accommodation> update(Long id, AccommodationDTO accommodation);
    void delete(Long id);
    Optional<Accommodation>  markAsRented(Long id);
}