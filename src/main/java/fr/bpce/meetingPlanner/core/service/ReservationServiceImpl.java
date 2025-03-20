package fr.bpce.meetingPlanner.core.service;

import fr.bpce.meetingPlanner.core.domain.Reservation;
import fr.bpce.meetingPlanner.core.domain.Salle;
import fr.bpce.meetingPlanner.core.persistance.SalleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final SalleRepository salleRepository;

    public ReservationServiceImpl(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    @Override
    public synchronized Optional<Salle> reserverSalle(String date, int startHour, int endHour, int nombrePersonne) {
        Optional<Salle> salleOptional = salleRepository.findAll().stream()
                .filter(salle -> salle.getCapaciteMax() >= nombrePersonne) // Vérifie si la salle peut accueillir le nombre de personnes
                .filter(salle -> salle.isAvailable(date, startHour, endHour)) // Vérifie la disponibilité pour le créneau horaire
                .min((s1, s2) -> Integer.compare(s1.getCapaciteMax(), s2.getCapaciteMax())); // Trouve la salle avec la capacité minimale

        salleOptional.ifPresent(salle -> addReservation(salle, date, startHour, endHour, nombrePersonne));

        return salleOptional;
    }

    private void addReservation(Salle salle, String date, int startHour, int endHour, int nombreDePersonnes) {
        Reservation reservation = new Reservation(date, startHour, endHour, nombreDePersonnes);
        salle.getReservations().add(reservation);
    }


}
