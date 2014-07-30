package org.test.business.entity.morphia;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class AddressEntityBean implements Serializable {

    private static final long serialVersionUID = 6014825721289910144L;

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

}
