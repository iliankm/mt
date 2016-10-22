export const PHONE_TYPES = {
	MOBILE: "MOBILE",
	LANDLINE: "LANDLINE"
}

export class Phone {

	/**
	 * Constructor for Phone
	 *
	 * @param {Object} data - Creation data
	 * @param {string} data.type - phone type: PHONE_TYPES
	 * @param {string} data.phone - phone number
	 */
	constructor(data) {
		this.type = data.type;
		this.phone = data.phone;
	}

	/**
	 * @return {Object}
	 */
	toJSON() {
		return {
			type: this.type,
			phone: this.phone
		};
	}
}
