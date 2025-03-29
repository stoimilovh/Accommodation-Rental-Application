package mk.ukim.finki.emt.lab.model.dto;

import jakarta.persistence.ManyToOne;
import mk.ukim.finki.emt.lab.model.Accommodation;

public class ReviewDTO {
    String comment;
    int rate;

    Long accommodation;

    public ReviewDTO() {
    }

    public ReviewDTO(String comment, int rate, Long accommodation) {
        this.comment = comment;
        this.rate = rate;
        this.accommodation = accommodation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Long getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Long accommodation) {
        this.accommodation = accommodation;
    }
}
