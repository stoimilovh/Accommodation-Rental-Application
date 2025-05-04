package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayReviewDTO(Long id, String comment, int rate, Long accommodation) {
    public static DisplayReviewDTO from(Review review) {
        return new DisplayReviewDTO(review.getId(),
                review.getComment(),
                review.getRate(),
                review.getAccommodation().getId()
        );
    }

    public static List<DisplayReviewDTO> from(List<Review> reviews) {
        return reviews.stream().map(DisplayReviewDTO::from).collect(Collectors.toList());
    }
}
