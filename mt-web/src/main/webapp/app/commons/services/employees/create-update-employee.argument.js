import {PHONE_TYPES, Phone} from 'app/commons/services/employees/phone.model.js';
import {Address} from 'app/commons/services/employees/address.model.js';

export class CreateUpdateEmployeeArgument {

	/**
	 * Constructor for CreateUpdateEmployeeArgument
	 *
	 * @param {Object} data - Creation data
	 * @param {string} data.name - employee name
	 * @param {string} data.gender - employee gender: GENDERS.MALE or GENDERS.FEMALE
	 * @param {Object} data.address - employee address
	 * @param {string} data.address.street - street
	 * @param {string} data.address.zip - zip
	 * @param {string} data.address.street - street
	 * @param {string} data.address.country - country
	 * @param {string} data.email - employee email
	 * @param {Object[]} data.phones - array of phones
	 * @param {string} data.phones[].type - phone type: PHONE_TYPES
	 * @param {string} data.phones[].phone - phone number
	 */
	constructor(data) {
		this.name = data && data.name || null;
		this.gender = data && data.gender || null;
		this.address = data && data.address ? new Address(data.address) : null;
		this.email = data && data.email || null;
		this.phones = data && data.phones ? data.phones.map(d => new Phone(d)) : null;
	}

	/**
	 * @return {Object}
	 */
	toJSON() {
		return {
			name: this.name,
			gender: this.gender,
			address: this.address.toJSON(),
			email: this.email,
			phones: this.phones.map(p => p.toJSON())
		};
	}
}