package com.udemy.multischema.model.types;

public enum StatusEnum {


    COMPLETED("Completed"), CANCELLED_BY_PATIENT("Cancelled"), RESCHEDULED_PATIENT("Rescheduled"),RESCHEDULED_DOCTOR("Rescheduled"),
    IN_PROGRESS("In Progress"), CREATED("In Queue"), CANCELLED_BY_DOCTOR("Cancelled");

    private String statusMessage;

    private StatusEnum(String statusMessage){
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
