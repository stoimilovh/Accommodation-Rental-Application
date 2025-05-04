package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Category;
import mk.ukim.finki.emt.lab.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDTO(String name, Category category, Long host, Integer numRooms, Boolean isRented) {

    public static CreateAccommodationDTO from(Accommodation accommodation) {
        return new CreateAccommodationDTO(accommodation.getName(), accommodation.getCategory(), accommodation.getHost().getId(), accommodation.getNumRooms(), accommodation.isRented());
    }

    public static List<CreateAccommodationDTO> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationDTO::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }
}
