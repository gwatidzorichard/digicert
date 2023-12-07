package com.digicert.hotel.booking.controller;

import com.digicert.hotel.booking.dto.ReservationDTO;
import com.digicert.hotel.booking.exceptions.ReservationCreationException;
import com.digicert.hotel.booking.exceptions.ReservationException;
import com.digicert.hotel.booking.exceptions.ResourceDeletionException;
import com.digicert.hotel.booking.exceptions.ResourceNotFoundException;
import com.digicert.hotel.booking.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private ReservationDTO validReservationDTO;
    private ReservationDTO updatedReservationDTO;
    private List<ReservationDTO> reservationDTOList;

    @BeforeEach
    void setUp() {
        validReservationDTO = new ReservationDTO(1L, "John Doe", LocalDate.now(), LocalDate.now().plusDays(3), "Acme Travels");
        updatedReservationDTO = new ReservationDTO(1L, "Jane Doe", LocalDate.now(), LocalDate.now().plusDays(4), "Acme Travels");
        reservationDTOList = Arrays.asList(validReservationDTO, updatedReservationDTO);
    }

    @Test
    void testCreateReservationWhenValidReservationThenReturnReservation() {
        when(reservationService.createReservation(validReservationDTO)).thenReturn(validReservationDTO);

        ReservationDTO result = reservationController.createReservation(validReservationDTO);

        assertEquals(validReservationDTO, result);
    }

    @Test
    void testCreateReservationWhenInvalidReservationThenThrowException() {
        ReservationDTO invalidReservationDTO = new ReservationDTO(null, null, null, null, null);
        when(reservationService.createReservation(invalidReservationDTO)).thenThrow(new ReservationCreationException("Invalid reservation data"));

        assertThrows(ReservationCreationException.class, () -> reservationController.createReservation(invalidReservationDTO));
    }

    @Test
    void testCreateReservationsWhenValidReservationsThenReturnReservations() {
        when(reservationService.createReservations(reservationDTOList)).thenReturn(reservationDTOList);

        List<ReservationDTO> result = reservationController.createReservations(reservationDTOList);

        assertEquals(reservationDTOList, result);
    }

    @Test
    void testCreateReservationsWhenInvalidReservationThenThrowException() {
        ReservationDTO invalidReservationDTO = new ReservationDTO(null, null, null, null, null);
        List<ReservationDTO> invalidList = Arrays.asList(validReservationDTO, invalidReservationDTO);
        when(reservationService.createReservations(invalidList)).thenThrow(new ReservationCreationException("Invalid reservation data"));

        assertThrows(ReservationCreationException.class, () -> reservationController.createReservations(invalidList));
    }

    @Test
    void testGetAllReservations() {
        when(reservationService.getAllReservations()).thenReturn(reservationDTOList);

        List<ReservationDTO> result = reservationController.getAllReservations();

        assertEquals(reservationDTOList, result);
    }

    @Test
    void testGetReservationWhenValidIdThenReturnReservation() {
        when(reservationService.getReservationById(1L)).thenReturn(validReservationDTO);

        ReservationDTO result = reservationController.getReservation(1L);

        assertEquals(validReservationDTO, result);
    }

    @Test
    void testGetReservationWhenInvalidIdThenThrowException() {
        when(reservationService.getReservationById(99L)).thenThrow(new ResourceNotFoundException("Reservation not found"));

        assertThrows(ReservationCreationException.class, () -> reservationController.getReservation(99L));
    }

    @Test
    void testUpdateReservationWhenValidIdAndReservationThenReturnUpdatedReservation() {
        when(reservationService.updateReservation(1L, updatedReservationDTO)).thenReturn(updatedReservationDTO);

        ReservationDTO result = reservationController.updateReservation(1L, updatedReservationDTO);

        assertEquals(updatedReservationDTO, result);
    }

    @Test
    void testUpdateReservationWhenInvalidIdThenThrowException() {
        when(reservationService.updateReservation(99L, validReservationDTO)).thenThrow(new ResourceNotFoundException("Reservation not found"));

        assertThrows(ReservationException.class, () -> reservationController.updateReservation(99L, validReservationDTO));
    }

    @Test
    void testDeleteReservationWhenValidIdThenNoException() {
        doNothing().when(reservationService).deleteReservation(1L);

        assertDoesNotThrow(() -> reservationController.deleteReservation(1L));
    }

    @Test
    void testDeleteReservationWhenInvalidIdThenThrowException() {
        doThrow(new ResourceNotFoundException("Reservation not found")).when(reservationService).deleteReservation(99L);

        assertThrows(ResourceDeletionException.class, () -> reservationController.deleteReservation(99L));
    }
}
