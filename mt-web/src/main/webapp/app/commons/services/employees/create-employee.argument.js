import {PHONE_TYPES, Phone} from 'app/commons/services/employees/phone.model.js';
import {Address} from 'app/commons/services/employees/address.model.js';

export class CreateEmployeeArgument {

	constructor(data) {
		this.name = data.name;
		this.gender = data.gender;
		this.address = new Address(data.address);
		this.email = data.email;
		this.phones = data.phones.map(d => new Phone(d));
	}

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