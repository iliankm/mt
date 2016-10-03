package org.mt.business.api.domain.employee;

/**
 *
 * Employee search criteria immutable object.
 *
 */
public class EmployeeSearchCriteria {

    private final String name;

    private final Gender gender;

    private final String email;

    private EmployeeSearchCriteria(Builder builder) {
	this.name = builder.name;
	this.gender = builder.gender;
	this.email = builder.email;
    }

    public static class Builder {

	private String name;
	private Gender gender;
	private String email;

	public Builder get() {
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

	public Builder email(String email) {
	    this.email = email;
	    return this;
	}

	public EmployeeSearchCriteria build() {
	    return new EmployeeSearchCriteria(this);
	}
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
