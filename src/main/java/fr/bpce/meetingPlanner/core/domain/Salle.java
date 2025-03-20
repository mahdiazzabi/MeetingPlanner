package fr.bpce.meetingPlanner.core.domain;

import java.util.Set;

public class Salle {

    private String nom;
    private int capaciteMax;

    public Salle(String nom, int capaciteMax) {
        this.nom = nom;
        this.capaciteMax = capaciteMax;
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
}
