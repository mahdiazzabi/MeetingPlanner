package fr.bpce.meetingPlanner.core.persistance;

import fr.bpce.meetingPlanner.core.domain.Salle;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SalleRepository {
    List<Salle> findAll();
}
