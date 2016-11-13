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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((city == null) ? 0 : city.hashCode());
	result = prime * result + ((country == null) ? 0 : country.hashCode());
	result = prime * result + ((street == null) ? 0 : street.hashCode());
	result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
	AddressArgument other = (AddressArgument) obj;
	if (city == null) {
	    if (other.city != null)
		return false;
	} else if (!city.equals(other.city))
	    return false;
	if (country == null) {
	    if (other.country != null)
		return false;
	} else if (!country.equals(other.country))
	    return false;
	if (street == null) {
	    if (other.street != null)
		return false;
	} else if (!street.equals(other.street))
	    return false;
	if (zip == null) {
	    if (other.zip != null)
		return false;
	} else if (!zip.equals(other.zip))
	    return false;
	return true;
    }

}
