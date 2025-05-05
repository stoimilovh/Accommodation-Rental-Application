package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt.lab.model.dto.CreateReviewDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayReviewDTO;
import mk.ukim.finki.emt.lab.service.application.ReviewApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewApplicationService reviewApplicationService;

    public ReviewController(ReviewApplicationService reviewService) {
        this.reviewApplicationService = reviewService;
    }

    @Operation(
            summary = "Get all reviews",
            description = "Returns a list of all reviews with their comments, ratings, and associated accommodation IDs."
    )
    @GetMapping
    public List<DisplayReviewDTO> findAll() {
        return reviewApplicationService.findAll();
    }

    @Operation(
            summary = "Get a review by ID",
            description = "Retrieves a review based on its unique ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisplayReviewDTO> findById(@PathVariable Long id) {
        return reviewApplicationService.findById(id).map(review -> ResponseEntity.ok().body(review))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a new review",
            description = "Creates a review using the provided comment, rating, and accommodation ID."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayReviewDTO> save(@RequestBody CreateReviewDTO createReviewDTO) {
        return reviewApplicationService.save(createReviewDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(
            summary = "Update an existing review",
            description = "Updates the comment and rating of a review based on its ID."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayReviewDTO> update(@PathVariable Long id, @RequestBody CreateReviewDTO createReviewDTO) {
        return this.reviewApplicationService.update(id, createReviewDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a review",
            description = "Deletes a review based on its ID."
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get average rating for accommodation",
            description = "Returns the average review rating for a specific accommodation by ID."
    )
    @GetMapping("/average/{id}")
    public Double average(@PathVariable Long id) {
        return reviewApplicationService.averageRating(id);
    }

}
