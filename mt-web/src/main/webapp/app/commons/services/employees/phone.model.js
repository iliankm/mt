export const PHONE_TYPES = {
	MOBILE: "MOBILE",
	LANDLINE: "LANDLINE"
}

export class Phone {

	constructor(data) {
		this.type = data.type;
		this.phone = data.phone;
	}

	toJSON() {
		return {
			type: this.type,
			phone: this.phone
		};
	}
}
