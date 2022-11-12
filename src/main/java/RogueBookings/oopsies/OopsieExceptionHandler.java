package RogueBookings.oopsies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class OopsieExceptionHandler {

    @ExceptionHandler(value = {OopsieRequestException.class})
    public ResponseEntity<Object> handleOopsieException(OopsieRequestException oopsie){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        OopsieException oopsieException = new OopsieException(
                oopsie.getMessage(),
                badRequest,
                ZonedDateTime.now()

        );
        return new ResponseEntity<>(oopsieException, badRequest);
    }
}
