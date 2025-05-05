package fr.bpce.meetingPlanner.core.controller;

import fr.bpce.meetingPlanner.core.domain.MeetingType;
import fr.bpce.meetingPlanner.core.domain.Salle;
import fr.bpce.meetingPlanner.core.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reserver")
    public ResponseEntity<String> reserverSalle(@RequestParam String date,
                                                @RequestParam int startHour,
                                                @RequestParam int endHour,
                                                @RequestParam int nombreDePersonnes,
                                                @RequestParam MeetingType typeReunion) {
        if (!isValidMeetingType(typeReunion)) {
            return ResponseEntity.badRequest().body("Type de réunion invalide.");
        }
        Optional<Salle> salle = reservationService.reserverSalle(date, startHour, endHour, nombreDePersonnes, typeReunion);
        return salle.map(value -> ResponseEntity.ok("Salle réservée : " + value.getNom() + " (Capacité: " + value.getCapaciteMax() + ")"))
                .orElseGet(() -> ResponseEntity.status(404).body("Aucune salle disponible pour " + nombreDePersonnes + " personnes."));
    }

    private boolean isValidMeetingType(MeetingType typeReunion) {
        return Arrays.asList(MeetingType.values()).contains(typeReunion);
    }

}
