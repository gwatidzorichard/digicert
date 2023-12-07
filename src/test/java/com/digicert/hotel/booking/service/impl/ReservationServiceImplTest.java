package com.digicert.hotel.booking.service.impl;

import com.digicert.hotel.booking.dto.ReservationDTO;
import com.digicert.hotel.booking.exceptions.*;
import com.digicert.hotel.booking.mapper.ReservationEntityMapper;
import com.digicert.hotel.booking.model.ReservationEntity;
import com.digicert.hotel.booking.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationEntityMapper reservationEntityMapper;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private ReservationDTO reservationDTO;
    private ReservationEntity reservationEntity;

    @BeforeEach
    void setUp() {
        reservationDTO = new ReservationDTO(1L, "John Doe", LocalDate.now(), LocalDate.now().plusDays(1), "Test Agency");
        reservationEntity = new ReservationEntity();
        reservationEntity.setId(1L);
        reservationEntity.setGuestName("John Doe");
        reservationEntity.setCheckInDate(LocalDate.now());
        reservationEntity.setCheckOutDate(LocalDate.now().plusDays(1));
        reservationEntity.setAgencyName("Test Agency");
    }

    @Test
    @DisplayName("Test createReservation when a new reservation is successfully created")
    void testCreateReservationWhenNewReservationThenSuccess() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(reservationEntityMapper.toEntity(any(ReservationDTO.class))).thenReturn(reservationEntity);
        when(reservationRepository.save(any(ReservationEntity.class))).thenReturn(reservationEntity);

        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);

        assertThat(createdReservation).isEqualTo(reservationDTO);
        verify(reservationRepository).save(reservationEntity);
    }

    @Test
    @DisplayName("Test createReservation when a reservation with the same id already exists")
    void testCreateReservationWhenExistingReservationThenException() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservationEntity));

        assertThatThrownBy(() -> reservationService.createReservation(reservationDTO))
                .isInstanceOf(ReservationCreationException.class)
                .hasMessageContaining("Reservation with id 1 already exists");
    }

    @Test
    @DisplayName("Test createReservations when a list of new reservations is successfully created")
    void testCreateReservationsWhenNewReservationsThenSuccess() {
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        reservationDTOList.add(reservationDTO);

        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(reservationEntityMapper.toEntity(any(ReservationDTO.class))).thenReturn(reservationEntity);
        when(reservationRepository.save(any(ReservationEntity.class))).thenReturn(reservationEntity);

        List<ReservationDTO> createdReservations = reservationService.createReservations(reservationDTOList);

        assertThat(createdReservations).isEqualTo(reservationDTOList);
        verify(reservationRepository, times(reservationDTOList.size())).save(reservationEntity);
    }

/*    @Test
    @DisplayName("Test createReservations when a reservation in the list already exists")
    void testCreateReservationsWhenExistingReservationThenException() {
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        reservationDTOList.add(reservationDTO);

        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservationEntity));

        assertThatThrownBy(() -> reservationService.createReservations(reservationDTOList))
                .isInstanceOf(ReservationException.class)
                .hasMessageContaining("Reservation with id 1 already exists");
    }*/
    @Test
    @DisplayName("Test getAllReservations when there are reservations in the database")
    void testGetAllReservationsWhenReservationsExistThenSuccess() {
        List<ReservationEntity> reservationEntityList = new ArrayList<>();
        reservationEntityList.add(reservationEntity);

        when(reservationRepository.findAll(any(Sort.class))).thenReturn(reservationEntityList);
        when(reservationEntityMapper.toDto(any(ReservationEntity.class))).thenReturn(reservationDTO);

        List<ReservationDTO> allReservations = reservationService.getAllReservations();

        assertThat(allReservations).hasSize(1);
        assertThat(allReservations.get(0)).isEqualTo(reservationDTO);
    }

    @Test
    @DisplayName("Test getAllReservations when there are no reservations in the database")
    void testGetAllReservationsWhenNoReservationsThenEmptyList() {
        when(reservationRepository.findAll(any(Sort.class))).thenReturn(new ArrayList<>());

        List<ReservationDTO> allReservations = reservationService.getAllReservations();

        assertThat(allReservations).isEmpty();
    }

    @Test
    @DisplayName("Test getReservationById when the reservation with the given id exists")
    void testGetReservationByIdWhenReservationExistsThenSuccess() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservationEntity));
        when(reservationEntityMapper.toDto(any(ReservationEntity.class))).thenReturn(reservationDTO);

        ReservationDTO foundReservation = reservationService.getReservationById(1L);

        assertThat(foundReservation).isEqualTo(reservationDTO);
    }

    @Test
    @DisplayName("Test getReservationById when the reservation with the given id does not exist")
    void testGetReservationByIdWhenReservationDoesNotExistThenException() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reservationService.getReservationById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Reservation with id 1 not found");
    }

/*    @Test
    @DisplayName("Test updateReservation when the reservation with the given id exists")
    void testUpdateReservationWhenReservationExistsThenSuccess() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservationEntity));
        when(reservationEntityMapper.toDto(any(ReservationEntity.class))).thenReturn(reservationDTO);
        doReturn(reservationEntity).when(reservationRepository).save(any(ReservationEntity.class));

        ReservationDTO updatedReservation = reservationService.updateReservation(1L, reservationDTO);

        assertThat(updatedReservation).isEqualTo(reservationDTO);
        verify(reservationRepository).save(reservationEntity);
    }*/

    @Test
    @DisplayName("Test updateReservation when the reservation with the given id does not exist")
    void testUpdateReservationWhenReservationDoesNotExistThenException() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reservationService.updateReservation(1L, reservationDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Reservation with id 1 could not be found");
    }

    @Test
    @DisplayName("Test deleteReservation when the reservation with the given id exists")
    void testDeleteReservationWhenReservationExistsThenSuccess() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservationEntity));
        doNothing().when(reservationRepository).delete(any(ReservationEntity.class));

        assertThatCode(() -> reservationService.deleteReservation(1L)).doesNotThrowAnyException();
        verify(reservationRepository).delete(reservationEntity);
    }

    @Test
    @DisplayName("Test deleteReservation when the reservation with the given id does not exist")
    void testDeleteReservationWhenReservationDoesNotExistThenException() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reservationService.deleteReservation(1L))
                .isInstanceOf(ResourceDeletionException.class)
                .hasMessageContaining("Reservation with ID 1 not found");
    }
}