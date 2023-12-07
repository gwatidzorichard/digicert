package com.digicert.hotel.booking.repository;

import com.digicert.hotel.booking.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
