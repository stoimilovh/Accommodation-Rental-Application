package mk.ukim.finki.emt.lab.model.exceptions;

public class AccommodationUnavailableException extends RuntimeException {
    public AccommodationUnavailableException(Long id) {
        super("Accommodation with ID " + id + " is already rented or does not exist.");
    }
}