package com.digicert.hotel.booking.controller;

import com.digicert.hotel.booking.dto.ReservationDTO;
import com.digicert.hotel.booking.exceptions.ReservationCreationException;
import com.digicert.hotel.booking.exceptions.ReservationException;
import com.digicert.hotel.booking.exceptions.ResourceDeletionException;
import com.digicert.hotel.booking.exceptions.ResourceNotFoundException;
import com.digicert.hotel.booking.service.ReservationService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Path("/booking")
@Component
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @POST
    @Path("/reservation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        log.info("creating reservation");
        try {
            return reservationService.createReservation(reservationDTO);
        } catch (ReservationCreationException e) {
            throw new ReservationCreationException(e.getMessage());
        }
    }

    @POST
    @Path("/reservations")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReservationDTO> createReservations(List<ReservationDTO> reservationDTO) {
        log.info("creating agency reservation");
        try {
            return reservationService.createReservations(reservationDTO);
        }catch (ReservationCreationException e){
            throw new ReservationCreationException(e.getMessage());
        }

    }

    @GET
    @Path("/reservations")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GET
    @Path("/reservation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public ReservationDTO getReservation(@PathParam("id") Long id) {
        try {
            return reservationService.getReservationById(id);
        } catch (ResourceNotFoundException e){
            throw new ReservationCreationException(e.getMessage());
        }
    }

    @PUT
    @Path("/reservation/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public ReservationDTO updateReservation(@PathParam("id") Long id, ReservationDTO reservationDTO) {
       try {
           return reservationService.updateReservation(id, reservationDTO);
       } catch (ResourceNotFoundException e){
           throw new ReservationException(e.getMessage());
       }
    }

    @DELETE
    @Path("/reservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation(@PathParam("id") Long id) {
        try {
            reservationService.deleteReservation(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceDeletionException(e.getMessage());
        }
    }
}

