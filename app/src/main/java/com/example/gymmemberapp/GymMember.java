package com.example.gymmemberapp;

public class GymMember {
    String nameString;
    String idString;
    String planString;

    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getPlanString() {
        return planString;
    }

    public void setPlanString(String planString) {
        this.planString = planString;
    }

    public GymMember(String nameString, String idString, String planString) {
        this.nameString = nameString;
        this.idString = idString;
        this.planString = planString;
    }

    public String toString() {
        return idString + " - " + nameString;
    }
}
