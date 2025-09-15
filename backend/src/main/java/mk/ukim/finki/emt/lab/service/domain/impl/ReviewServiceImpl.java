package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Review;
import mk.ukim.finki.emt.lab.model.dto.CreateReviewDTO;
import mk.ukim.finki.emt.lab.repository.ReviewRepository;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final AccommodationService accommodationService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, AccommodationService accommodationService) {
        this.reviewRepository = reviewRepository;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Review> findAll(){
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> findById(Long id){
        return reviewRepository.findById(id);
    }

    @Override
    public Optional<Review> save(Review revieww){
        Optional<Accommodation> accommodationOptional = this.accommodationService.findById(revieww.getAccommodation().getId());
        if (accommodationOptional.isPresent()) {
            Review review = new Review(revieww.getComment(), revieww.getRate(), accommodationOptional.get());
            return Optional.of(this.reviewRepository.save(review));
        }
        return Optional.empty();
    }
    public Optional<Review> update(Long id, Review createReview){
        return this.reviewRepository.findById(id).map(review -> {
            if(createReview.getComment() != null){
                review.setComment(createReview.getComment());
            }
            if(createReview.getRate() >= 0){
                review.setRate(createReview.getRate());
            }
            if(createReview.getAccommodation() != null) {
                review.setAccommodation(accommodationService.findById(createReview.getAccommodation().getId()).orElse(null));
            }
            return reviewRepository.save(review);
        });
    }

    @Override
    public void delete(Long id){
        this.reviewRepository.deleteById(id);
    }

    @Override
    public double averageRating(Long id){
        List<Review> reviews = this.reviewRepository.findAll();

        List<Review> filteredReviews = reviews.stream()
                .filter(review -> review.getAccommodation().getId().equals(id))
                .collect(Collectors.toList());

        if (filteredReviews.isEmpty()) {
            return 0.0;
        }

        return filteredReviews.stream()
                .mapToDouble(Review::getRate)
                .average()
                .orElse(0.0);
    }

}
