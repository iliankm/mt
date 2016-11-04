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
	this.street = data && data.street || null;
	this.zip = data && data.zip || null;
	this.city = data && data.city || null;
	this.country = data && data.country || null;
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