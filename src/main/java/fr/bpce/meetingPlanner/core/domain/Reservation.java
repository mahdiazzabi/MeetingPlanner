package fr.bpce.meetingPlanner.core.domain;

public class Reservation {

    private String date;
    private int startHour;
    private int endHour;
    private int nombreDePersonnes;
    private MeetingType typeReunion;

    public Reservation(String date, int startHour, int endHour, int nombreDePersonnes, MeetingType typeReunion) {
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.nombreDePersonnes = nombreDePersonnes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getNombreDePersonnes() {
        return nombreDePersonnes;
    }

    public void setNombreDePersonnes(int nombreDePersonnes) {
        this.nombreDePersonnes = nombreDePersonnes;
    }

    public MeetingType getTypeReunion() {
        return typeReunion;
    }

    public void setTypeReunion(MeetingType typeReunion) {
        this.typeReunion = typeReunion;
    }

    // Vérification des conflits de réservation
    public boolean conflictsWith(String date, int startHour, int endHour) {
        // Vérifier si les dates sont les mêmes et si les horaires se chevauchent
        // Ajouter une contrainte de 1 heure avant et après la réservation
        return this.date.equals(date) && (
                (startHour < this.endHour + 1 && endHour > this.startHour - 1));
}

    @Override
    public String toString() {
        return "Réservation{" +
                "date='" + date + '\'' +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                ", nombreDePersonnes=" + nombreDePersonnes +
                '}';
    }
}
