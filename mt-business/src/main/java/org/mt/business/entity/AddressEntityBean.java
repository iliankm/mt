package org.mt.business.entity;

import javax.validation.constraints.NotNull;

import org.mongodb.morphia.annotations.Embedded;
import org.mt.business.api.entity.AddressEntity;

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

    /**
     * Default no-args constructor needed by persistence framework
     */
	private AddressEntityBean() {}

	private AddressEntityBean(Builder builder) {

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

		public AddressEntityBean build() {

			return new AddressEntityBean(this);
		}
	}

	@Override
	public String getStreet() {

		return street;
	}

	@Override
	public String getZip() {
		return zip;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public String getCountry() {
		return country;
	}

}
