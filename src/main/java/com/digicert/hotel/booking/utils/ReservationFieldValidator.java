package com.digicert.hotel.booking.utils;

import com.digicert.hotel.booking.dto.ReservationDTO;

public class ReservationFieldValidator {

    public static void validateReservationDTO(ReservationDTO reservationDTO) {
        if (reservationDTO.getGuestName().isBlank()) {
            throw new IllegalArgumentException("Guest name is required");
        }

        if (reservationDTO.getCheckInDate() == null) {
            throw new IllegalArgumentException("Check-in date is required");
        }

        if (reservationDTO.getCheckOutDate() == null) {
            throw new IllegalArgumentException("Check-out date is required");
        }


    }
}