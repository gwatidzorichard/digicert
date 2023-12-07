package com.digicert.hotel.booking.mapper;

import com.digicert.hotel.booking.dto.ReservationDTO;
import com.digicert.hotel.booking.model.ReservationEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationEntityMapper {

    ReservationEntity toEntity(ReservationDTO reservationDTO);

    ReservationDTO toDto(ReservationEntity reservationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ReservationEntity partialUpdate(ReservationDTO reservationDTO, @MappingTarget ReservationEntity reservationEntity);

    default void updateExistingEntity(ReservationDTO reservationDTO, ReservationEntity reservationEntity) {
        // Map non-null fields from DTO to Entity
        partialUpdate(reservationDTO, reservationEntity);
    }


}
