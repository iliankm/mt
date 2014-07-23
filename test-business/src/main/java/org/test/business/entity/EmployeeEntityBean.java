package org.test.business.entity;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.test.business.api.domain.util.Gender;

@Entity(value = "employee", noClassnameStored = false)
public class EmployeeEntityBean extends BaseEntityBean {

    private static final long serialVersionUID = -2454104054295746441L;

    @Indexed
    private String name;

    private Gender gender;

    @Embedded
    private AddressEntityBean address;

    @Indexed
    private String email;

    @Embedded
    private List<PhoneEntityBean> phones;

    private long salary;

    private Set<ObjectId> substituteIds;

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

    public AddressEntityBean getAddress() {
	if (address == null) {
	    address = new AddressEntityBean();
	}

	return address;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public List<PhoneEntityBean> getPhones() {
	if (phones == null) {
	    phones = new LinkedList<>();
	}

	return phones;
    }

    public long getSalary() {
	return salary;
    }

    public void setSalary(long salary) {
	this.salary = salary;
    }

    public Set<ObjectId> getSubstituteIds() {
	if (substituteIds == null) {
	    substituteIds = new LinkedHashSet<>();
	}

	return substituteIds;
    }

}
