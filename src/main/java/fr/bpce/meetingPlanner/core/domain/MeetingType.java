package fr.bpce.meetingPlanner.core.domain;

import java.util.EnumSet;
import java.util.Set;

public enum MeetingType {
    VC(EnumSet.of(Equipment.ECRAN, Equipment.PIEUVRE, Equipment.WEBCAM), true),
    SPEC(EnumSet.of(Equipment.TABLEAU), false),
    RS(EnumSet.noneOf(Equipment.class), true),
    RC(EnumSet.of(Equipment.TABLEAU, Equipment.ECRAN, Equipment.PIEUVRE), true);

    private final Set<Equipment> requiredEquipment;
    private final boolean onSiteRequired;

    MeetingType(Set<Equipment> requiredEquipment, boolean onSiteRequired) {
        this.requiredEquipment = requiredEquipment;
        this.onSiteRequired = onSiteRequired;
    }

    public Set<Equipment> getRequiredEquipment() {
        return requiredEquipment;
    }

    public boolean isOnSiteRequired() {
        return onSiteRequired;
    }

    public boolean requiresEquipment(Equipment equipment) {
        return requiredEquipment.contains(equipment);
    }
}
