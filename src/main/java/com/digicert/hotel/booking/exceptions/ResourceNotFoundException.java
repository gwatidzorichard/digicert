package com.digicert.hotel.booking.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
