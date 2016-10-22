export class Address {

	/**
	 * Constructor for Address
	 *
	 * @param {Object} data - Creation data
	 * @param {string} data.street - street
	 * @param {string} data.zip - zip
	 * @param {string} data.street - street
	 * @param {string} data.country - country
	 */
	constructor(data) {
		this.street = data.street;
		this.zip = data.zip;
		this.city = data.city;
		this.country = data.country;
	}

	/**
	 * @return {Object}
	 */
	toJSON() {
		return {
			street: this.street,
			zip: this.zip,
			city: this.city,
			country: this.country
		};
	}
}