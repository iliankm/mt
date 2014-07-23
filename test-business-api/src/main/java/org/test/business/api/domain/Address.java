package org.test.business.api.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Address implements Serializable{

    private static final long serialVersionUID = -1267552031136559623L;

    @NotNull(message = "validation_address_street_is_mandatory")
    private String street;

    @NotNull(message = "validation_address_zip_is_mandatory")
    private String zip;

    @NotNull(message = "validation_address_street_is_mandatory")
    private String city;

    @NotNull(message = "validation_address_country_is_mandatory")
    private String country;

    public String getStreet() {

	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getZip() {
	return zip;
    }

    public void setZip(String zip) {
	this.zip = zip;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
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
	Address other = (Address) obj;
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
