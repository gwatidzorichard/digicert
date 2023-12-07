package com.digicert.hotel.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class ReservationEntity {
    @Id
    @JsonProperty("id")
    private Long id;
    @JsonProperty("guestName")
    private String guestName;
    @JsonProperty("checkInDate")
    private LocalDate checkInDate;
    @JsonProperty("checkOutDate")
    private LocalDate checkOutDate;
    @JsonProperty("agencyName")
    private String agencyName;

}
