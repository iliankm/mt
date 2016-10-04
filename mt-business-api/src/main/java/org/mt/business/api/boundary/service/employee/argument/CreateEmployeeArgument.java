package org.mt.business.api.boundary.service.employee.argument;

import java.util.List;

import org.mt.business.api.domain.employee.Gender;

/**
 * Immutable CreateEmployeeArgument
 *
 */
public class CreateEmployeeArgument {

    private String name;

    private Gender gender;

    private AddressArgument address;

    private String email;

    private List<PhoneArgument> phones;

    /**
     * Default no-args constructor
     */
    private CreateEmployeeArgument() {
    }

    private CreateEmployeeArgument(Builder builder) {
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

	public CreateEmployeeArgument build() {
	    return new CreateEmployeeArgument(this);
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
        return phones;
    }

}
