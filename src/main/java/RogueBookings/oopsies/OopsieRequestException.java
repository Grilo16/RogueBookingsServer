package RogueBookings.oopsies;

public class OopsieRequestException extends RuntimeException{
    public OopsieRequestException(String message) {
        super(message);
    }

    public OopsieRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
