package com.digicert.hotel.booking.exceptions;

public class ReservationCreationException extends RuntimeException{
    public ReservationCreationException(String message) {
        super(message);
    }

    public ReservationCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
