package naayadaa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by AnastasiiaDepenchuk on 26-Apr-18.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class JournalServiceException extends Exception {

    public JournalServiceException(String message) {
        super(message);
    }

    public JournalServiceException(String message, Throwable cause) {
        super(message, cause);
    }



}
