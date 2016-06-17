package org.test.business.api.entity;

public interface AddressEntity extends Entity {

    public String getStreet();

    public void setStreet(String street);

    public String getZip();

    public void setZip(String zip);

    public String getCity();

    public void setCity(String city);

    public String getCountry();

    public void setCountry(String country);

}
