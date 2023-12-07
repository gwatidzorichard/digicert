package com.digicert.hotel.booking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ErrorResponse {
    private String error;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;


    }

    public ErrorResponse() {

    }
}
