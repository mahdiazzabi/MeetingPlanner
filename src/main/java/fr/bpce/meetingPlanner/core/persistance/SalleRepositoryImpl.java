package fr.bpce.meetingPlanner.core.persistance;

import fr.bpce.meetingPlanner.core.domain.Salle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SalleRepositoryImpl implements SalleRepository {

    // Liste statique de salles dans un prmier temps TODO définir l'accés au données
    private static final List<Salle> salles = new ArrayList<>();

    static {
        // Initialisation des salles
        salles.add(new Salle("E1001", 23));
        salles.add(new Salle("E1002", 10));
        salles.add(new Salle("E1003", 8));
        salles.add(new Salle("E1004", 4));
        salles.add(new Salle("E2001", 4));
        salles.add(new Salle("E2002", 15));
        salles.add(new Salle("E2003", 7));
        salles.add(new Salle("E2004", 9));
        salles.add(new Salle("E3001", 13));
        salles.add(new Salle("E3002", 8));
        salles.add(new Salle("E3003", 9));
        salles.add(new Salle("E3004", 4));
    }

    @Override
    public List<Salle> findAll() {
        return salles;
    }
}
