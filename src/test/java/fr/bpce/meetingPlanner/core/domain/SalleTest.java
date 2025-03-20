package fr.bpce.meetingPlanner.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalleTest {
    @Test
    void isAvailable_shouldReturnFalse_whenDateIsWeekend() {
        Salle salle = new Salle("Salle A", 20);
        boolean result = salle.isAvailable("2025-03-23", 10, 11);
        assertFalse(result);
    }

    @Test
    void isAvailable_shouldReturnFalse_whenStartHourBefore8() {
        Salle salle = new Salle("Salle A", 20);
        boolean result = salle.isAvailable("2025-03-10", 7, 8);
        assertFalse(result);
    }

    @Test
    void isAvailable_shouldReturnFalse_whenEndHourAfter20() {
        Salle salle = new Salle("Salle A", 20);
        boolean result = salle.isAvailable("2025-03-10", 20, 21);
        assertFalse(result);
    }

    @Test
    void isAvailable_shouldReturnFalse_whenStartHourEqualsEndHour() {
        Salle salle = new Salle("Salle A", 20);
        boolean result = salle.isAvailable("2025-03-10", 10, 10);
        assertFalse(result);
    }

    @Test
    void isAvailable_shouldReturnTrue_whenWithinAllowedHoursAndNoConflict() {
        Salle salle = new Salle("Salle A", 20);
        boolean result = salle.isAvailable("2025-03-10", 11, 12);
        assertTrue(result);
    }

    @Test
    void isAvailable_shouldReturnFalse_whenReservationConflicts() {
        Salle salle = new Salle("Salle A", 20);
        salle.getReservations().add(new Reservation("2025-03-10", 11, 12, 4));
        boolean result = salle.isAvailable("2025-03-10", 11, 12);
        assertFalse(result);
    }

    @Test
    void isAvailable_shouldReturnTrue_whenDifferentDate() {
        Salle salle = new Salle("Salle A", 20);
        salle.getReservations().add(new Reservation("2025-03-11", 10, 11, 4));
        boolean result = salle.isAvailable("2025-03-10", 10, 11);
        assertTrue(result);
    }

}