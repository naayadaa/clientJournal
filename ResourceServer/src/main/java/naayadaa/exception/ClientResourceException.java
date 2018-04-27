package naayadaa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientResourceException extends Exception {

    public ClientResourceException() {
        super();
    }

    public ClientResourceException(String message) {
        super(message);
    }

    public ClientResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
