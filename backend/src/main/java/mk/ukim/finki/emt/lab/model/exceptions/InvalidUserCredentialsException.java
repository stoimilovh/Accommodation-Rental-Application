package mk.ukim.finki.emt.lab.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {

    public InvalidUserCredentialsException() {
        super("Invalid username or password");
    }
}