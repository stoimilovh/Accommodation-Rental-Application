package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Review;
import mk.ukim.finki.emt.lab.model.dto.CreateReviewDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayReviewDTO;
import mk.ukim.finki.emt.lab.repository.ReviewRepository;
import mk.ukim.finki.emt.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab.service.application.ReviewApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewApplicationServiceImpl implements ReviewApplicationService {
    private final ReviewService reviewService;
    private final AccommodationService accommodationService;

    public ReviewApplicationServiceImpl(ReviewService reviewService, AccommodationService accommodationService) {
        this.reviewService = reviewService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<DisplayReviewDTO> findAll() {
        return reviewService.findAll().stream()
                .map(DisplayReviewDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayReviewDTO> findById(Long id) {
        return reviewService.findById(id).map(DisplayReviewDTO::from);
    }

    @Override
    public Optional<DisplayReviewDTO> save(CreateReviewDTO createReviewDTO) {
        Optional<Accommodation> accommodation = accommodationService.findById(createReviewDTO.accommodation());

        if (accommodation.isPresent()) {
            return reviewService.save(createReviewDTO.toReview(accommodation.get()))
                    .map(DisplayReviewDTO::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayReviewDTO> update(Long id, CreateReviewDTO createReviewDTO) {
        Optional<Accommodation> accommodation = accommodationService.findById(createReviewDTO.accommodation());

        return reviewService.update(id,
                        createReviewDTO.toReview(accommodation.orElse(null)))
                .map(DisplayReviewDTO::from);
    }

    @Override
    public void delete(Long id) {
        reviewService.delete(id);
    }

    @Override
    public double averageRating(Long accommodationId) {
        return reviewService.averageRating(accommodationId);
    }
}

