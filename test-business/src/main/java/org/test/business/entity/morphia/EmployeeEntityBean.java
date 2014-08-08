package org.test.business.entity.morphia;

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
import org.test.business.api.domain.util.Gender;

@Entity(value = "employee", noClassnameStored = false)
public class EmployeeEntityBean extends BaseEntityBean {

    private static final long serialVersionUID = -2454104054295746441L;

    @Indexed
    @NotNull(message = "validation_employee_name_is_mandatory")
    private String name;

    @NotNull(message = "validation_employee_gender_is_mandatory")
    private Gender gender;

    @Valid
    @Embedded
    private AddressEntityBean address;

    @Pattern (regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "validation_employee_email_invalid")
    @Indexed
    private String email;

    @Valid
    @Embedded
    private List<PhoneEntityBean> phones;

    @Min(value = 0, message = "validation_employee_salary_invalid")
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

	return address;
    }

    public void setAddress(AddressEntityBean address) {
	this.address = address;
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
