package com.digicert.hotel.booking.service.impl;

import com.digicert.hotel.booking.dto.ReservationDTO;
import com.digicert.hotel.booking.exceptions.*;
import com.digicert.hotel.booking.mapper.ReservationEntityMapper;
import com.digicert.hotel.booking.model.ReservationEntity;
import com.digicert.hotel.booking.repository.ReservationRepository;
import com.digicert.hotel.booking.service.ReservationService;
import com.digicert.hotel.booking.utils.ReservationFieldValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationEntityMapper reservationEntityMapper;
    private final ReservationRepository reservationRepository;



    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Long id = reservationDTO.getId();

        try {
            if (reservationRepository.findById(id).isPresent()) {
                throw new ReservationCreationException("Reservation with id " + id + " already exists");
            }

            ReservationFieldValidator.validateReservationDTO(reservationDTO);
            ReservationEntity reservationEntity = reservationEntityMapper.toEntity(reservationDTO);
            reservationEntity.setId(reservationDTO.getId());
            reservationRepository.save(reservationEntity);
            log.info("Reservation created: " + reservationDTO);

            return reservationDTO;
        } catch (ReservationCreationException e) {

            throw new ReservationCreationException("Reservation with id " + id + " already exists", e);

        }catch (IllegalArgumentException e){

            throw new IllegalArgumentException(e.getMessage());

        }catch (Exception e){

            throw new RuntimeException(e.getMessage());
        }
    }
    public List<ReservationDTO> createReservations(List<ReservationDTO> reservationsList) {

        List<ReservationDTO> reservationList = new ArrayList<>();

        for (ReservationDTO reservationDTO : reservationsList) {
            Optional<ReservationEntity> byId = reservationRepository.findById(reservationDTO.getId());

            try {
                if (byId.isPresent()) {
                    ReservationDTO existingReservation = reservationEntityMapper.toDto(byId.get());
                    throw new ReservationException("Reservation with id " + existingReservation.getId() + " already exists");
                } else {
                    ReservationFieldValidator.validateReservationDTO(reservationDTO);

                    if (reservationDTO.getAgencyName().isBlank()) {
                        throw new IllegalArgumentException("Agency name is required");
                    }

                    ReservationEntity res = reservationEntityMapper.toEntity(reservationDTO);
                    res.setId(reservationDTO.getId());

                    reservationEntityMapper.updateExistingEntity(reservationDTO, res);

                    reservationRepository.save(res);
                    reservationList.add(reservationDTO);
                }
            } catch (ReservationException e) {
                throw new ReservationException("Reservation with id " + byId.get().getId() + " already exists");
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } return reservationList;
    }


        public List<ReservationDTO> getAllReservations( ) {

      Sort sort = Sort.by(Sort.Order.asc("guestName"));
        return reservationRepository
                .findAll(sort)
                .stream()
                .map(reservationEntityMapper::toDto)
                .collect(Collectors
                        .toList());
    }

    public ReservationDTO getReservationById(Long id) {

        var optionalReservation = reservationRepository.findById(id);
        return optionalReservation
                .map(reservationEntityMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with id " + id + " not found"));
    }

    public ReservationDTO updateReservation(Long id, ReservationDTO updatedReservationDTO) {

        Optional<ReservationEntity> existingReservationOptional = reservationRepository.findById(id);

        if (existingReservationOptional.isPresent()) {

            ReservationDTO existingReservation = existingReservationOptional.map(reservationEntityMapper::toDto).get();
            // Update properties if not null
            if (Objects.nonNull(updatedReservationDTO.getId()) ) {

                existingReservation.setId(updatedReservationDTO.getId());
            }
            if (Objects.nonNull(updatedReservationDTO.getCheckOutDate()) ) {

                existingReservation.setCheckOutDate(updatedReservationDTO.getCheckOutDate());
            }
            if (Objects.nonNull(updatedReservationDTO.getCheckInDate()) ) {

                existingReservation.setCheckInDate(updatedReservationDTO.getCheckInDate());
            }
            if (Objects.nonNull(updatedReservationDTO.getGuestName()) ) {

                existingReservation.setGuestName(updatedReservationDTO.getGuestName());

            }
            log.info("Reservation updated: " + existingReservation);

            ReservationEntity existingReservationEntity = reservationEntityMapper.toEntity(existingReservation);
            reservationRepository.save(existingReservationEntity);

            return existingReservation;
        } else {
            throw new ResourceNotFoundException("Reservation with id " + id+ " could not be found");
        }
    }

    public void deleteReservation(Long id) {
        try {
            ReservationEntity reservation = reservationRepository.findById(id).orElse(null);

            if (reservation != null) {

                reservationRepository.delete(reservation);
                log.info("Reservation with ID " + id +" deleted successfully");

            } else {

                throw new ResourceDeletionException("Reservation with ID " + id + " not found.");

            }

        } catch (ReservationException e){

            log.error("Error deleting reservation with ID " + id + ": " + e.getMessage());
        }
    }
}
