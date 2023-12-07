package com.digicert.hotel.booking.exceptions;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        if (exception instanceof ReservationCreationException) {

            return handleReservationCreationException((ReservationCreationException) exception);

        } else if (exception instanceof ReservationException) {

            return handleReservationException((ReservationException) exception);

        } else if(exception instanceof ResourceNotFoundException) {

            return handleResourceNotFoundException((ResourceNotFoundException) exception);

        }   else if (exception instanceof ResourceDeletionException){

            return handleResourceDeletionException((ResourceDeletionException) exception);

        } else if (exception instanceof IllegalArgumentException) {

            return handleIllegalArgumentException((IllegalArgumentException)exception);

        } else if(exception instanceof NotFoundException) {

            return handleNotFoundException((NotFoundException) exception);

        } else return handleUnexpectedException(exception);
    }

    private Response handleReservationCreationException(ReservationCreationException rce) {
        ErrorResponse errorResponse = new ErrorResponse("Reservation Creation Exception", rce.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response handleReservationException(ReservationException re) {
        ErrorResponse errorResponse = new ErrorResponse("Reservation Exception", re.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response handleNotFoundException(NotFoundException nf) {
        ErrorResponse errorResponse = new ErrorResponse("Not Found", nf.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    private Response handleResourceNotFoundException(ResourceNotFoundException re) {
        ErrorResponse errorResponse = new ErrorResponse("Reservation not found", re.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response handleUnexpectedException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    private Response handleResourceDeletionException(ResourceDeletionException rde) {
        ErrorResponse errorResponse = new ErrorResponse("Resource Deletion Error",rde.getMessage() );
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response handleIllegalArgumentException(IllegalArgumentException iae) {
        ErrorResponse errorResponse = new ErrorResponse("Field validation error",iae.getMessage() );
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}

