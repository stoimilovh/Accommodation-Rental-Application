package mk.ukim.finki.emt.lab.service.domain;


import mk.ukim.finki.emt.lab.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll();
    Optional<Review> findById(Long id);
    Optional<Review> save(Review Review);
    Optional<Review> update(Long id, Review createReview);
    void delete(Long id);
    double averageRating(Long id);
}
