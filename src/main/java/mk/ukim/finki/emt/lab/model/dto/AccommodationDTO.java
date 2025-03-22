package mk.ukim.finki.emt.lab.model.dto;

import jakarta.persistence.ManyToOne;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.model.Host;

public class AccommodationDTO {
    private String name;
    private Category category;

    private Long host;

    private Integer numRooms;

    public AccommodationDTO() {
    }

    public AccommodationDTO(String name, Category category, Long host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getHost() {
        return host;
    }

    public void setHost(Long host) {
        this.host = host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }
}
