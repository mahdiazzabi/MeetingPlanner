package fr.bpce.meetingPlanner.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void conflictsWith_shouldReturnTrue_whenReservationConflicts() {
        Reservation reservation = new Reservation("2023-10-10", 10, 12, 5);
        assertTrue(reservation.conflictsWith("2023-10-10", 11, 13));
    }

    @Test
    void conflictsWith_shouldReturnFalse_whenDifferentDate() {
        Reservation reservation = new Reservation("2023-10-10", 10, 12, 5);
        assertFalse(reservation.conflictsWith("2023-10-11", 10, 12));
    }

    @Test
    void conflictsWith_shouldReturnTrue_whenStartHourOverlaps() {
        Reservation reservation = new Reservation("2023-10-10", 10, 12, 5);
        assertTrue(reservation.conflictsWith("2023-10-10", 9, 11));
    }

    @Test
    void conflictsWith_shouldReturnTrue_whenEndHourOverlaps() {
        Reservation reservation = new Reservation("2023-10-10", 10, 12, 5);
        assertTrue(reservation.conflictsWith("2023-10-10", 11, 13));
    }

    @Test
    void conflictsWith_shouldReturnTrue_whenReservationEnclosesTimeRange() {
        Reservation reservation = new Reservation("2023-10-10", 10, 12, 5);
        assertTrue(reservation.conflictsWith("2023-10-10", 9, 13));
    }

    @Test
    void conflictsWith_shouldReturnTrue_whenTimeRangeEnclosesReservation() {
        Reservation reservation = new Reservation("2023-10-10", 10, 12, 5);
        assertTrue(reservation.conflictsWith("2023-10-10", 10, 11));
    }


    @Test
    void conflictsWith_shouldReturnTrue_whenCleaningBufferAfterReservation() {
        Reservation reservation = new Reservation("2023-10-10", 10, 12, 5);
        assertTrue(reservation.conflictsWith("2023-10-10", 12, 13));
    }
}