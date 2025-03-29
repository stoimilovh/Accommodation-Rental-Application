package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Accommodation;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.model.Review;
import mk.ukim.finki.emt.lab.model.dto.ReviewDTO;
import mk.ukim.finki.emt.lab.repository.ReviewRepository;
import mk.ukim.finki.emt.lab.service.AccommodationService;
import mk.ukim.finki.emt.lab.service.ReviewService;
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
    public Optional<Review> save(ReviewDTO reviewDTO){
        Optional<Accommodation> accommodationOptional = this.accommodationService.findById(reviewDTO.getAccommodation());
        if (accommodationOptional.isPresent()) {
            Review review = new Review(reviewDTO.getComment(), reviewDTO.getRate(), accommodationOptional.get());
            return Optional.of(this.reviewRepository.save(review));
        }
        return Optional.empty();
    }
    public Optional<Review> update(Long id, ReviewDTO reviewDTO){
        return this.reviewRepository.findById(id).map(review -> {
            if(reviewDTO.getComment() != null){
                review.setComment(reviewDTO.getComment());
            }
            if(reviewDTO.getRate() >= 0){
                review.setRate(reviewDTO.getRate());
            }
            if(reviewDTO.getAccommodation() != null) {
                review.setAccommodation(accommodationService.findById(reviewDTO.getAccommodation()).orElse(null));
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
