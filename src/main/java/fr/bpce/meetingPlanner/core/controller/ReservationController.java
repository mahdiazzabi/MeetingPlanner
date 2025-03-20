package fr.bpce.meetingPlanner.core.controller;

import fr.bpce.meetingPlanner.core.domain.Salle;
import fr.bpce.meetingPlanner.core.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                                @RequestParam int nombreDePersonnes){
        Optional<Salle> salle = reservationService.reserverSalle(date, startHour, endHour, nombreDePersonnes);
        return salle.map(value -> ResponseEntity.ok("Salle réservée : " + value.getNom() + " (Capacité: " + value.getCapaciteMax() + ")"))
                .orElseGet(() -> ResponseEntity.status(404).body("Aucune salle disponible pour " + nombreDePersonnes + " personnes."));
    }

}
