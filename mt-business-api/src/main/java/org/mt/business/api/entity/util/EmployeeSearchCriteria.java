package org.mt.business.api.entity.util;

import java.io.Serializable;

public class EmployeeSearchCriteria implements Serializable {

    private static final long serialVersionUID = 814715872993628096L;

    private String name;

    private Gender gender;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
