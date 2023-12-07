package com.digicert.hotel.booking.service;

import com.digicert.hotel.booking.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAllReservations();

    ReservationDTO getReservationById(Long id);

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    public List <ReservationDTO> createReservations(List<ReservationDTO> reservationDTO);

    ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO);

    void deleteReservation(Long id);
}
