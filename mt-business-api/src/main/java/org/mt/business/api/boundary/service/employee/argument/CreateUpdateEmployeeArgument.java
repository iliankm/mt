package org.mt.business.api.boundary.service.employee.argument;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.mt.business.api.domain.RegExp;
import org.mt.business.api.domain.employee.Gender;

/**
 * Immutable CreateEmployeeArgument
 *
 */
public class CreateUpdateEmployeeArgument {

    @NotNull(message = "validation_employee_id_number_is_mandatory")
    private String identificationNumber;

    @NotNull(message = "validation_employee_name_is_mandatory")
    private String name;

    @NotNull(message = "validation_employee_gender_is_mandatory")
    private Gender gender;

    @Pattern(regexp = RegExp.EMAIL, message = "validation_employee_email_invalid")
    private String email;

    /**
     * Default no-args constructor
     */
    private CreateUpdateEmployeeArgument() {
    }

    private CreateUpdateEmployeeArgument(Builder builder) {
	this.identificationNumber = builder.identificationNumber;
	this.name = builder.name;
	this.gender = builder.gender;
	this.email = builder.email;
    }

    public static class Builder {

	private String identificationNumber;
	private String name;
	private Gender gender;
	private String email;

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

	public Builder email(String email) {
	    this.email = email;
	    return this;
	}

	public CreateUpdateEmployeeArgument build() {
	    return new CreateUpdateEmployeeArgument(this);
	}
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }
}
