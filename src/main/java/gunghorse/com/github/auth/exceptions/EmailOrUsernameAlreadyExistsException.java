package gunghorse.com.github.auth.exceptions;


public class EmailOrUsernameAlreadyExistsException extends RuntimeException {
    public EmailOrUsernameAlreadyExistsException(String message) {
        super(message);
    }
}
