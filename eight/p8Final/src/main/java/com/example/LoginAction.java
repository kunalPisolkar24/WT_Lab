package com.example;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
	private String fullName;
    private String mobileNumber;
    private String emailId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String execute() {
        if (fullName.equals("Kunal Pisolkar") && mobileNumber.equals("7058892395") && emailId.equals("kpisolkar24@gmail.com")) {
            return SUCCESS;
        } else {
            addActionError("Invalid input. Please try again.");
            return INPUT;
        }
    }
}
