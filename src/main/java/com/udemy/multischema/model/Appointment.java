package com.udemy.multischema.model;

import com.udemy.multischema.model.types.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Document(collection = "Appointment")
public class Appointment {

    @Id
    private String id;

    private LocalDateTime appointmentDateTime;

    private LocalDateTime appointmentEndDateTime;

    private String doctorId;

    private String patientMobileNumber;

    private String patientName;

    private Integer durationInMinutes;

    private String purpose;

    private StatusEnum status;

    private Instant createDateTime;

    private Instant updateDateTime;

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +

                ", doctorId='" + doctorId + '\'' +
                ", patientMobileNumber='" + patientMobileNumber + '\'' +
                ", patientName='" + patientName + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", purpose='" + purpose + '\'' +
                ", status=" + status +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                '}';
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public LocalDateTime getAppointmentEndDateTime() {
        return appointmentEndDateTime;
    }

    public void setAppointmentEndDateTime(LocalDateTime appointmentEndDateTime) {
        this.appointmentEndDateTime = appointmentEndDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientMobileNumber() {
        return patientMobileNumber;
    }

    public void setPatientMobileNumber(String patientMobileNumber) {
        this.patientMobileNumber = patientMobileNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Instant getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Instant createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Instant getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Instant updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
