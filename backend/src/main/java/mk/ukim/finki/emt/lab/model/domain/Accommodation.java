package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Host host;

    private Integer numRooms;
    private boolean isRented;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public Accommodation(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = false;
    }
    public Accommodation(String name, Category category, Host host, Integer numRooms, boolean isRented) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = isRented;
    }
}
