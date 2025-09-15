package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

public record CreateReviewDTO(String comment, int rate, Long accommodation) {

    public static CreateReviewDTO from(Review review) {
        return new CreateReviewDTO(
                review.getComment(),
                review.getRate(),
                review.getAccommodation().getId()
        );
    }

    public static List<CreateReviewDTO> from(List<Review> reviews) {
        return reviews.stream().map(CreateReviewDTO::from).collect(Collectors.toList());
    }

    public Review toReview(Accommodation accommodation) {
        return new Review(comment, rate, accommodation);
    }
}
