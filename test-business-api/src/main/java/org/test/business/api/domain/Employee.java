package org.test.business.api.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.bson.types.ObjectId;
import org.test.business.api.domain.util.Gender;
import org.test.business.api.domain.util.Identifiable;

public class Employee implements Identifiable, Serializable {

    private static final long serialVersionUID = -7850236434515729926L;

    private ObjectId id;

    private String name;

    private Gender gender;

    private Address address;

    private String email;

    private List<Phone> phones;

    private long salary;

    @Override
    public ObjectId getId() {
	return id;
    }

    public void setId(ObjectId id) {
	this.id = id;
    }

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

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public List<Phone> getPhones() {

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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((gender == null) ? 0 : gender.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Employee other = (Employee) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (gender != other.gender)
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

}
