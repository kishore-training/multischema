package com.udemy.multischema.vo;

public class DoctorUserRequestVO {


    private String userName;

    private String mobileNumber;

    private String emailId;

    private String password;

    private String specialization;

    private String referralPatientNo;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getReferralPatientNo() {
        return referralPatientNo;
    }

    public void setReferralPatientNo(String referralPatientNo) {
        this.referralPatientNo = referralPatientNo;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
