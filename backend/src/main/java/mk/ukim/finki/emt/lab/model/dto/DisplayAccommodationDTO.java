package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Accommodation;
import mk.ukim.finki.emt.lab.model.domain.Category;
import mk.ukim.finki.emt.lab.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDTO (Long id, String name, Category category, Long host, Integer numRooms, Boolean isRented) {

    public static DisplayAccommodationDTO from(Accommodation accommodation) {
        return new DisplayAccommodationDTO(accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms(),
                accommodation.isRented());
    }

    public static List<DisplayAccommodationDTO> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDTO::from).collect(Collectors.toList());
    }

}



