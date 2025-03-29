package mk.ukim.finki.emt.lab.service;


import mk.ukim.finki.emt.lab.model.Review;
import mk.ukim.finki.emt.lab.model.dto.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll();
    Optional<Review> findById(Long id);
    Optional<Review> save(ReviewDTO reviewDTO);
    Optional<Review> update(Long id, ReviewDTO reviewDTO);
    void delete(Long id);
    double averageRating(Long id);
}
