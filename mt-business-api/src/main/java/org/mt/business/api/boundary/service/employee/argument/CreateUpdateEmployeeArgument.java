package org.mt.business.api.boundary.service.employee.argument;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.mt.business.api.domain.RegExp;
import org.mt.business.api.domain.employee.Gender;

/**
 * Immutable CreateEmployeeArgument
 *
 */
public class CreateUpdateEmployeeArgument {

    @NotNull(message = "validation_employee_name_is_mandatory")
    private String name;

    @NotNull(message = "validation_employee_gender_is_mandatory")
    private Gender gender;

    @NotNull(message = "validation_employee_address_is_mandatory")
    @Valid
    private AddressArgument address;

    @Pattern(regexp = RegExp.EMAIL, message = "validation_employee_email_invalid")
    private String email;

    @Valid
    private List<PhoneArgument> phones;

    /**
     * Default no-args constructor
     */
    private CreateUpdateEmployeeArgument() {
    }

    private CreateUpdateEmployeeArgument(Builder builder) {
	this.name = builder.name;
	this.gender = builder.gender;
	this.address = builder.address;
	this.email = builder.email;
	this.phones = builder.phones;
    }

    public static class Builder {

	private String name;
	private Gender gender;
	private AddressArgument address;
	private String email;
	private List<PhoneArgument> phones;

	public static Builder get() {
	    return new Builder();
	}

	public Builder name(String name) {
	    this.name = name;
	    return this;
	}

	public Builder gender(Gender gender) {
	    this.gender = gender;
	    return this;
	}

	public Builder address(AddressArgument address) {
	    this.address = address;
	    return this;
	}

	public Builder email(String email) {
	    this.email = email;
	    return this;
	}

	public Builder phones(List<PhoneArgument> phones) {
	    this.phones = phones;
	    return this;
	}

	public CreateUpdateEmployeeArgument build() {
	    return new CreateUpdateEmployeeArgument(this);
	}
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public AddressArgument getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public List<PhoneArgument> getPhones() {
        return phones == null ? Collections.emptyList() : Collections.unmodifiableList(phones);
    }

}
