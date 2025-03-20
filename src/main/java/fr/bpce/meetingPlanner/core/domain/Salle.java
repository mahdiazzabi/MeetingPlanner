package fr.bpce.meetingPlanner.core.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Salle {

    private String nom;
    private int capaciteMax;
    private List<Reservation> reservations;

    public Salle(String nom, int capaciteMax) {
        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.reservations = new ArrayList<>();
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isAvailable(String date, int startHour, int endHour) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        // Vérifier si la date est un week-end
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return false;
        }

        // Vérifier si les heures sont entre 8h et 20h
        if (startHour < 8 || endHour > 20 || startHour >= endHour) {
            return false;
        }

        // Vérifier les conflits de réservation existants
        return reservations.stream()
                .noneMatch(reservation -> reservation.conflictsWith(date, startHour, endHour));
    }
}
