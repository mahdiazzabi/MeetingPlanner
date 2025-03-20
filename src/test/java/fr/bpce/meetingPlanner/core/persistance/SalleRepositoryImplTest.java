package fr.bpce.meetingPlanner.core.persistance;

import fr.bpce.meetingPlanner.core.domain.Salle;
import fr.bpce.meetingPlanner.core.service.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReservationServiceImplTest {

    @Mock
    private SalleRepository salleRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void reserverSalle_shouldReturnSalle_whenAvailable() {
        Salle salle1 = new Salle("Salle A", 10);
        Salle salle2 = new Salle("Salle B", 20);
        List<Salle> salles = Arrays.asList(salle1, salle2);

        when(salleRepository.findAll()).thenReturn(salles);

        Optional<Salle> result = reservationService.reserverSalle(15);

        assertEquals(salle2, result.orElse(null));
    }

    @Test
    void reserverSalle_shouldReturnEmpty_whenNoSalleAvailable() {
        Salle salle1 = new Salle("Salle A", 10);
        List<Salle> salles = Arrays.asList(salle1);

        when(salleRepository.findAll()).thenReturn(salles);

        Optional<Salle> result = reservationService.reserverSalle(15);

        assertEquals(Optional.empty(), result);
    }

    @Test
    void reserverSalle_shouldReturnSalleWithExactCapacity_whenExactMatch() {
        Salle salle1 = new Salle("Salle A", 10);
        Salle salle2 = new Salle("Salle B", 20);
        List<Salle> salles = Arrays.asList(salle1, salle2);

        when(salleRepository.findAll()).thenReturn(salles);

        Optional<Salle> result = reservationService.reserverSalle(20);

        assertEquals(salle2, result.orElse(null));
    }

    @Test
    void reserverSalle_shouldReturnSmallestSalle_whenMultipleSallesAvailable() {
        Salle salle1 = new Salle("Salle A", 10);
        Salle salle2 = new Salle("Salle B", 20);
        Salle salle3 = new Salle("Salle C", 30);
        List<Salle> salles = Arrays.asList(salle1, salle2, salle3);

        when(salleRepository.findAll()).thenReturn(salles);

        Optional<Salle> result = reservationService.reserverSalle(15);

        assertEquals(salle2, result.orElse(null));
    }
}