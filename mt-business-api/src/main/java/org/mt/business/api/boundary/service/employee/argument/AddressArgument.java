package org.mt.business.api.boundary.service.employee.argument;

import javax.validation.constraints.NotNull;

public class AddressArgument {

    @NotNull(message = "validation_address_street_is_mandatory")
    private String street;

    @NotNull(message = "validation_address_zip_is_mandatory")
    private String zip;

    @NotNull(message = "validation_address_street_is_mandatory")
    private String city;

    @NotNull(message = "validation_address_country_is_mandatory")
    private String country;

    /**
     * Default no-args constructor
     */
    private AddressArgument() {
    }

    private AddressArgument(Builder builder) {

	this.street = builder.street;
	this.zip = builder.zip;
	this.city = builder.city;
	this.country = builder.country;
    }

    /**
     * Builder for AddressEntityBean
     */
    public static class Builder {

	private String street;
	private String zip;
	private String city;
	private String country;

	public static Builder get() {

	    return new Builder();
	}

	public Builder street(String street) {

	    this.street = street;
	    return this;
	}

	public Builder zip(String zip) {

	    this.zip = zip;
	    return this;
	}

	public Builder city(String city) {

	    this.city = city;
	    return this;
	}

	public Builder country(String country) {

	    this.country = country;
	    return this;
	}

	public AddressArgument build() {

	    return new AddressArgument(this);
	}
    }

    public String getStreet() {

	return street;
    }

    public String getZip() {
	return zip;
    }

    public String getCity() {
	return city;
    }

    public String getCountry() {
	return country;
    }

}
