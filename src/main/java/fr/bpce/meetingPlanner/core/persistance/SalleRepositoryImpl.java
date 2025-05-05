package fr.bpce.meetingPlanner.core.persistance;

import fr.bpce.meetingPlanner.core.domain.Equipment;
import fr.bpce.meetingPlanner.core.domain.Salle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Repository
public class SalleRepositoryImpl implements SalleRepository {

    // Liste statique de salles dans un prmier temps TODO définir l'accés au données
    private static final List<Salle> salles = new ArrayList<>();

    static {
        // Initialisation des salles avec les équipements
        salles.add(new Salle("E1001", 23, EnumSet.noneOf(Equipment.class)));
        salles.add(new Salle("E1002", 10, EnumSet.of(Equipment.ECRAN)));
        salles.add(new Salle("E1003", 8, EnumSet.of(Equipment.PIEUVRE)));
        salles.add(new Salle("E1004", 4, EnumSet.of(Equipment.TABLEAU)));
        salles.add(new Salle("E2001", 4, EnumSet.noneOf(Equipment.class)));
        salles.add(new Salle("E2002", 15, EnumSet.of(Equipment.ECRAN, Equipment.WEBCAM)));
        salles.add(new Salle("E2003", 7, EnumSet.noneOf(Equipment.class)));
        salles.add(new Salle("E2004", 9, EnumSet.of(Equipment.TABLEAU)));
        salles.add(new Salle("E3001", 13, EnumSet.of(Equipment.ECRAN, Equipment.WEBCAM, Equipment.PIEUVRE)));
        salles.add(new Salle("E3002", 8, EnumSet.noneOf(Equipment.class)));
        salles.add(new Salle("E3003", 9, EnumSet.of(Equipment.ECRAN, Equipment.PIEUVRE)));
        salles.add(new Salle("E3004", 4, EnumSet.noneOf(Equipment.class)));
    }

    @Override
    public List<Salle> findAll() {
        return salles;
    }
}
