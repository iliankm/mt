package org.mt.business.domain.employee;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mt.business.api.domain.RegExp;
import org.mt.business.api.domain.employee.Address;
import org.mt.business.api.domain.employee.Employee;
import org.mt.business.api.domain.employee.Gender;
import org.mt.business.api.domain.employee.Phone;
import org.mt.business.domain.BaseEntityBean;

/**
 * Immutable EmployeeEntity
 *
 */
@Entity(value = "employee", noClassnameStored = false)
public class EmployeeEntityBean extends BaseEntityBean implements Employee {

    private static final long serialVersionUID = -2454104054295746441L;

    @NotNull(message = "validation_employee_id_number_is_mandatory")
    @Indexed(unique = true)
    private String identificationNumber;

    @NotNull(message = "validation_employee_name_is_mandatory")
    @Indexed
    private String name;

    @NotNull(message = "validation_employee_gender_is_mandatory")
    private Gender gender;

    @Pattern(regexp = RegExp.EMAIL, message = "validation_employee_email_invalid")
    @Indexed
    private String email;

    @Valid
    @Embedded
    private AddressEntityBean address;

    @Embedded
    @Valid
    private List<PhoneEntityBean> phones;

    private Set<ObjectId> substituteIds;

    /*
     * Default no-args constructor needed by persistence framework
     */
    private EmployeeEntityBean() {
    }

    private EmployeeEntityBean(Builder builder) {

	this.identificationNumber = builder.identificationNumber;
	this.name = builder.name;
	this.gender = builder.gender;
	this.address = builder.address;
	this.email = builder.email;
	this.phones = Collections.unmodifiableList(builder.phones);
	this.substituteIds = builder.substituteIds != null
		? builder.substituteIds.stream().map(sid -> new ObjectId(sid)).collect(Collectors.toSet()) : null;
    }

    /**
     * Builder for EmployeeEntity
     */
    public static class Builder {

	private String identificationNumber;
	private String name;
	private Gender gender;
	private AddressEntityBean address;
	private String email;
	private List<PhoneEntityBean> phones;
	private Set<String> substituteIds;

	public static Builder get() {

	    return new Builder();
	}

	public Builder identificationNumber(String identificationNumber) {

	    this.identificationNumber = identificationNumber;
	    return this;
	}

	public Builder name(String name) {

	    this.name = name;
	    return this;
	}

	public Builder gender(Gender gender) {

	    this.gender = gender;
	    return this;
	}

	public Builder address(AddressEntityBean address) {

	    this.address = address;
	    return this;
	}

	public Builder email(String email) {

	    this.email = email;
	    return this;
	}

	public Builder phones(List<PhoneEntityBean> phones) {

	    this.phones = phones;
	    return this;
	}


	public Builder substituteIds(Set<String> substituteIds) {

	    this.substituteIds = substituteIds;
	    return this;
	}

	public EmployeeEntityBean build() {

	    return new EmployeeEntityBean(this);
	}
    }

    @Override
    public String getName() {

	return name;
    }

    @Override
    public Gender getGender() {

	return gender;
    }

    @Override
    public Address getAddress() {

	return address;
    }

    @Override
    public String getEmail() {

	return email;
    }

    @Override
    public List<? extends Phone> getPhones() {

	return phones != null ? Collections.unmodifiableList(phones) : Collections.emptyList();
    }

    @Override
    public String getIdentificationNumber() {

	return identificationNumber;
    }

    @Override
    public Set<String> getSubstituteIds() {

	return substituteIds != null ? substituteIds.stream().map(oid -> oid.toHexString()).collect(Collectors.toSet())
		: Collections.emptySet();
    }
}
