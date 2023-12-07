package com.digicert.hotel.booking.exceptions;

public class ResourceDeletionException  extends RuntimeException{
    public ResourceDeletionException(String message) {
        super(message);
    }

    public ResourceDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
