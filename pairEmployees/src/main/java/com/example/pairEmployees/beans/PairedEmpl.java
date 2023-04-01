package com.example.pairEmployees.beans;

public class PairedEmpl {
    int emlpOneId;
    int emlpTwoId;
    int projectId;
    int daysWorked;

    public PairedEmpl(int emlpOneId, int emlpTwoId, int projectId, int daysWorked) {
        this.emlpOneId = emlpOneId;
        this.emlpTwoId = emlpTwoId;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public PairedEmpl() {
    }

    public int getEmlpOneId() {
        return emlpOneId;
    }

    public void setEmlpOneId(int emlpOneId) {
        this.emlpOneId = emlpOneId;
    }

    public int getEmlpTwoId() {
        return emlpTwoId;
    }

    public void setEmlpTwoId(int emlpTwoId) {
        this.emlpTwoId = emlpTwoId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    @Override
    public String toString() {
        return "PairedEmpl{" +
                "emlpOneId=" + emlpOneId +
                ", emlpTwoId=" + emlpTwoId +
                ", projectId=" + projectId +
                ", daysWorked=" + daysWorked +
                '}';
    }
}
