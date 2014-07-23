package org.test.business.entity;

import java.io.Serializable;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class AddressEntityBean implements Serializable {

    private static final long serialVersionUID = 6014825721289910144L;

    private String street;

    private String zip;

    private String city;

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
