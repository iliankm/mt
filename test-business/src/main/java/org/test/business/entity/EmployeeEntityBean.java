package org.test.business.entity;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.test.business.api.entity.AddressEntity;
import org.test.business.api.entity.EmployeeEntity;
import org.test.business.api.entity.PhoneEntity;
import org.test.business.api.entity.util.Gender;

@Entity(value = "employee", noClassnameStored = false)
public class EmployeeEntityBean extends BaseEntityBean implements
	EmployeeEntity {

    @NotNull(message = "validation_employee_name_is_mandatory")
    @Indexed
    private String name;

    @NotNull(message = "validation_employee_gender_is_mandatory")
    private Gender gender;

    @NotNull(message = "validation_employee_address_is_mandatory")
    @Valid
    @Embedded
    private AddressEntityBean address;

    @Pattern (regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "validation_employee_email_invalid")
    @Indexed
    private String email;

    @Embedded
    @Valid
    private List<PhoneEntityBean> phones;

    @Min(value = 0, message = "validation_employee_salary_invalid")
    private long salary;

    private Set<ObjectId> substituteIds;

    @Override
    public String getName() {
	return name;
    }

    @Override
    public void setName(String name) {
	this.name = name;
    }

    @Override
    public Gender getGender() {
	return gender;
    }

    @Override
    public void setGender(Gender gender) {
	this.gender = gender;
    }

    @Override
    public AddressEntity getAddress() {
	if (address == null) {
	    address = new AddressEntityBean();
	}

	return address;
    }
    
    @Override
    public String getEmail() {
	return email;
    }

    @Override
    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public List<? extends PhoneEntity> getPhones() {
	if (phones == null) {
	    phones = new LinkedList<>();
	}

	return phones;
    }

    @Override
    public long getSalary() {
	return salary;
    }

    @Override
    public void setSalary(long salary) {
	this.salary = salary;
    }

    @Override
    public Set<ObjectId> getSubstituteIds() {
	if (substituteIds == null) {
	    substituteIds = new LinkedHashSet<>();
	}

	return substituteIds;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result
		+ ((name == null) ? 0 : name.hashCode());
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
	EmployeeEntityBean other = (EmployeeEntityBean) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

}
