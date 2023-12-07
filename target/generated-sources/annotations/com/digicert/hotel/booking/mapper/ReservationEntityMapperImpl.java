package com.digicert.hotel.booking.mapper;

import com.digicert.hotel.booking.dto.ReservationDTO;
import com.digicert.hotel.booking.model.ReservationEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-07T17:08:42+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class ReservationEntityMapperImpl implements ReservationEntityMapper {

    @Override
    public ReservationEntity toEntity(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        ReservationEntity reservationEntity = new ReservationEntity();

        return reservationEntity;
    }

    @Override
    public ReservationDTO toDto(ReservationEntity reservationEntity) {
        if ( reservationEntity == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        return reservationDTO;
    }

    @Override
    public ReservationEntity partialUpdate(ReservationDTO reservationDTO, ReservationEntity reservationEntity) {
        if ( reservationDTO == null ) {
            return reservationEntity;
        }

        return reservationEntity;
    }
}
