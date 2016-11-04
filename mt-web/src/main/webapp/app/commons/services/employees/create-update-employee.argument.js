import {PHONE_TYPES, Phone} from 'app/commons/services/employees/phone.model.js';
import {Address} from 'app/commons/services/employees/address.model.js';

export class CreateUpdateEmployeeArgument {

	/**
	 * Constructor for CreateUpdateEmployeeArgument
	 *
	 * @param {Object} data - Creation data
	 * @param {string} data.identificationNumber - employee identification number
	 * @param {string} data.name - employee name
	 * @param {string} data.gender - employee gender: GENDERS.MALE or GENDERS.FEMALE
	 * @param {string} data.email - employee email
	 */
	constructor(data) {
	    this.identificationNumber = data && data.identificationNumber || null;
	    this.name = data && data.name || null;
	    this.gender = data && data.gender || null;
	    this.email = data && data.email || null;
	}

	/**
	 * @return {Object}
	 */
	toJSON() {
	    return {
		identificationNumber: this.identificationNumber,
		name: this.name,
		gender: this.gender,
		email: this.email,
	    };
	}
}