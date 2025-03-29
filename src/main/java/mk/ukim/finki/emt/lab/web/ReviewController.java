package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.Host;
import mk.ukim.finki.emt.lab.model.Review;
import mk.ukim.finki.emt.lab.model.dto.HostDTO;
import mk.ukim.finki.emt.lab.model.dto.ReviewDTO;
import mk.ukim.finki.emt.lab.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> findAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable Long id) {
        Optional<Review> review = reviewService.findById(id);
        return review.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Review> save(@RequestBody ReviewDTO reviewDTO) {
        Optional<Review> createdReview = reviewService.save(reviewDTO);
        return createdReview.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Review> update(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        Optional<Review> updatedReview = reviewService.update(id, reviewDTO);
        return updatedReview.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/average/{id}")
    public Double average(@PathVariable Long id) {
        return reviewService.averageRating(id);
    }

}
