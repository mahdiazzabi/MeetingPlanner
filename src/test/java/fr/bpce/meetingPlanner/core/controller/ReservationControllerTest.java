package fr.bpce.meetingPlanner.core.controller;

import fr.bpce.meetingPlanner.core.domain.Salle;
import fr.bpce.meetingPlanner.core.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void reserverSalle_shouldReturnOkResponse_whenSalleIsAvailable() {
        //GIVEN
        Salle salle = new Salle("Salle A", 20);
        when(reservationService.reserverSalle(10)).thenReturn(Optional.of(salle));
        //THEN
        ResponseEntity<String> response = reservationController.reserverSalle(10);
        //Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Salle réservée : Salle A (Capacité: 20)", response.getBody());
    }

    @Test
    void reserverSalle_shouldReturnNotFoundResponse_whenNoSalleIsAvailable() {
        //GIVEN
        when(reservationService.reserverSalle(10)).thenReturn(Optional.empty());
        //THEN
        ResponseEntity<String> response = reservationController.reserverSalle(10);
        //Assert
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Aucune salle disponible pour 10 personnes.", response.getBody());
    }
}