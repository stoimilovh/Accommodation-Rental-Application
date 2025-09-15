package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String comment;
    int rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id", nullable = false)
    Accommodation accommodation;

    public Review(String comment, int rate, Accommodation accommodation) {
        this.comment = comment;
        this.rate = rate;
        this.accommodation = accommodation;
    }
}
