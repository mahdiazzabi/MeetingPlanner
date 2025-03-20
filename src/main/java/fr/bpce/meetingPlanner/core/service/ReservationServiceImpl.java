package fr.bpce.meetingPlanner.core.service;

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
    public Optional<Salle> reserverSalle(int nombrePersonne) {
        // Récupérer la liste des salles
        // filtrer par nombre de personne
        // Renvoyer la premier salle qui correspond au min
        return salleRepository.findAll().stream()
                .filter(salle -> salle.getCapaciteMax() >= nombrePersonne)
                .min((salle1, salle2) -> Integer.compare(salle1.getCapaciteMax(), salle2.getCapaciteMax()));
    }
}
