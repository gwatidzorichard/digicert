package com.digicert.hotel.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.digicert.hotel.booking.model.ReservationEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO implements Serializable {
    private Long id;
    private String guestName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String agencyName;
}