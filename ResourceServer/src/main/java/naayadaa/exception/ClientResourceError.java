package naayadaa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ClientResourceError extends Exception{

    public ClientResourceError() {
        super();
    }

    public ClientResourceError(String message) {
        super(message);
    }

    public ClientResourceError(String message, Throwable cause) {
        super(message, cause);
    }
}
