package org.test.business.entity;

import javax.validation.constraints.NotNull;

import org.mongodb.morphia.annotations.Embedded;
import org.test.business.api.entity.AddressEntity;

@Embedded
public class AddressEntityBean implements AddressEntity {

    private static final long serialVersionUID = 6014825721289910144L;

    @NotNull(message = "validation_address_street_is_mandatory")
    private String street;

    @NotNull(message = "validation_address_zip_is_mandatory")
    private String zip;

    @NotNull(message = "validation_address_street_is_mandatory")
    private String city;

    @NotNull(message = "validation_address_country_is_mandatory")
    private String country;

    @Override
    public String getStreet() {

	return street;
    }

    @Override
    public void setStreet(String street) {
	this.street = street;
    }

    @Override
    public String getZip() {
	return zip;
    }

    @Override
    public void setZip(String zip) {
	this.zip = zip;
    }

    @Override
    public String getCity() {
	return city;
    }

    @Override
    public void setCity(String city) {
	this.city = city;
    }

    @Override
    public String getCountry() {
	return country;
    }

    @Override
    public void setCountry(String country) {
	this.country = country;
    }

}
