package fr.bpce.meetingPlanner.core.service;

import fr.bpce.meetingPlanner.core.domain.MeetingType;
import fr.bpce.meetingPlanner.core.domain.Salle;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ReservationService {
    Optional<Salle> reserverSalle(String date, int startHour, int endHour, int nombrePersonne, MeetingType typeReunion);
}
