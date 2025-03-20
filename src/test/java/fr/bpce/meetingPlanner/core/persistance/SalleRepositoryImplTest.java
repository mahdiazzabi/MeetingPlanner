package fr.bpce.meetingPlanner.core.persistance;

import fr.bpce.meetingPlanner.core.domain.Equipment;
import fr.bpce.meetingPlanner.core.domain.MeetingType;
import fr.bpce.meetingPlanner.core.domain.Salle;
import fr.bpce.meetingPlanner.core.service.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.EnumSet;
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
    void reserverSalle_shouldReturnSalle_whenAvailableWithRequiredEquipment() {
        Salle salle1 = new Salle("Salle A", 10);
        salle1.setEquipements(EnumSet.of(Equipment.ECRAN, Equipment.TABLEAU));
        Salle salle2 = new Salle("Salle B", 20);
        salle2.setEquipements(EnumSet.of(Equipment.ECRAN, Equipment.TABLEAU, Equipment.PIEUVRE));
        List<Salle> salles = Arrays.asList(salle1, salle2);

        when(salleRepository.findAll()).thenReturn(salles);

        Optional<Salle> result = reservationService.reserverSalle("2023-10-10", 9, 10, 15, MeetingType.RC);

        assertEquals(salle2, result.orElse(null));
    }

    @Test
    void reserverSalle_shouldReturnEmpty_whenNoSalleWithRequiredEquipment() {
        Salle salle1 = new Salle("Salle A", 10);
        salle1.setEquipements(EnumSet.of(Equipment.ECRAN));
        Salle salle2 = new Salle("Salle B", 20);
        salle2.setEquipements(EnumSet.of(Equipment.TABLEAU));
        List<Salle> salles = Arrays.asList(salle1, salle2);

        when(salleRepository.findAll()).thenReturn(salles);

        Optional<Salle> result = reservationService.reserverSalle("2023-10-10", 9, 10, 15, MeetingType.RC);

        assertEquals(Optional.empty(), result);
    }

    @Test
    void reserverSalle_shouldReturnSalleWithExactCapacityAndRequiredEquipment() {
        Salle salle1 = new Salle("Salle A", 10);
        salle1.setEquipements(EnumSet.of(Equipment.ECRAN, Equipment.TABLEAU));
        Salle salle2 = new Salle("Salle B", 20);
        salle2.setEquipements(EnumSet.of(Equipment.ECRAN, Equipment.TABLEAU, Equipment.PIEUVRE));
        List<Salle> salles = Arrays.asList(salle1, salle2);

        when(salleRepository.findAll()).thenReturn(salles);

        Optional<Salle> result = reservationService.reserverSalle("2023-10-10", 9, 10, 20, MeetingType.RC);

        assertEquals(salle2, result.orElse(null));
    }

    @Test
    void reserverSalle_shouldReturnSmallestSalleWithRequiredEquipment_whenMultipleSallesAvailable() {
        Salle salle1 = new Salle("Salle A", 10);
        salle1.setEquipements(EnumSet.of(Equipment.ECRAN, Equipment.TABLEAU));
        Salle salle2 = new Salle("Salle B", 20);
        salle2.setEquipements(EnumSet.of(Equipment.ECRAN, Equipment.TABLEAU, Equipment.PIEUVRE));
        Salle salle3 = new Salle("Salle C", 30);
        salle3.setEquipements(EnumSet.of(Equipment.ECRAN, Equipment.TABLEAU, Equipment.PIEUVRE, Equipment.WEBCAM));
        List<Salle> salles = Arrays.asList(salle1, salle2, salle3);

        when(salleRepository.findAll()).thenReturn(salles);

        Optional<Salle> result = reservationService.reserverSalle("2023-10-10", 9, 10, 15, MeetingType.RC);

        assertEquals(salle2, result.orElse(null));
    }
}