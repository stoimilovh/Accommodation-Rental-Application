package mk.ukim.finki.emt.lab.service.application;


import mk.ukim.finki.emt.lab.model.domain.Review;
import mk.ukim.finki.emt.lab.model.dto.CreateReviewDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewApplicationService {
    List<DisplayReviewDTO> findAll();
    Optional<DisplayReviewDTO> findById(Long id);
    Optional<DisplayReviewDTO> save(CreateReviewDTO createReviewDTO);
    Optional<DisplayReviewDTO> update(Long id, CreateReviewDTO createReviewDTO);
    void delete(Long id);
    double averageRating(Long id);
}
