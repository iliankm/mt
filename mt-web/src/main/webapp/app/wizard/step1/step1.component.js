import {Component} from '@angular/core';
import {CountrySelectComponent} from 'app/commons/components/country-select/country-select.component';
import {ValidateEmailDirective} from 'app/commons/directives/validate-email/validate-email.directive';

export class Step1Component {

	constructor() {

		this.RES = MT.Resources;

		this.data = {
				name: "",
				gender: "",
				address: {
					country: "",
					city: "",
					street: "",
					zip: ""
				},
				phones: []
		};

		this.phoneTypes = {
			MOBILE: "MOBILE",
			LANDLINE: "LANDLINE"
		};

		this.phone = {
				number: "",
				type: ""
		};
	}

	ngAfterViewInit() {
		//initialize tooltips
		$('[data-toggle="tooltip"]').tooltip();
	}

	onAddPhone(form) {

		this.data.phones.push({number: this.phone.number, type: this.phone.type});

		form.reset();
	}

	onDeletePhone(ind) {

		this.data.phones.splice(ind, 1);
	}
}

Step1Component.annotations = [
                            	new Component({
                            		selector: 'step1',
                            		templateUrl: 'app/wizard/step1/step1.template.html',
                            		styleUrls:  [],
                            		directives: [CountrySelectComponent, ValidateEmailDirective]
                            	})
];
