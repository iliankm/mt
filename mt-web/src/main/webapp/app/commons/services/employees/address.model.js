export class Address {

	constructor(data) {
		this.street = data.street;
		this.zip = data.zip;
		this.city = data.city;
		this.country = data.country;
	}

	toJSON() {
		return {
			street: this.street,
			zip: this.zip,
			city: this.city,
			country: this.country
		};
	}
}