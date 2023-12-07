package com.digicert.hotel.booking.exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class ReservationCreationExceptionMapper implements ExceptionMapper<ReservationCreationException> {

    @Override
    public Response toResponse(ReservationCreationException exception) {
        ErrorResponse errorResponse = new ErrorResponse
                ("Reservation Creation Error", exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
