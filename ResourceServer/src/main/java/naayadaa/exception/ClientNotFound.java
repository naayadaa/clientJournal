package naayadaa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFound extends ClientResourceException {

    public ClientNotFound() {
        super("Client not found");
    }

    public ClientNotFound(String message) {
        super(message);
    }
}
