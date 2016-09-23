import {Component} from '@angular/core';
import {CountrySelectComponent} from 'app/commons/components/country-select/country-select.component';
import {ValidateEmailDirective} from 'app/commons/directives/validate-email/validate-email.directive';
import {ValidatePhoneDirective} from 'app/commons/directives/validate-phone/validate-phone.directive';

export class Step1Component {

	constructor() {

		this.RES = MT.Resources;

		this.phoneTypes = {
			MOBILE: "MOBILE",
			LANDLINE: "LANDLINE"
		};

		this.phones = [];
	}

	ngAfterViewInit() {
		//initialize tooltips
		$('[data-toggle="tooltip"]').tooltip();
	}

	onPhoneFormSubmit(form) {

		this.phones.push({number: form.value.phoneNumber, type: form.value.phoneType});

		form.reset();
	}

	onDeletePhone(ind) {

		this.phones.splice(ind, 1);
	}
}

Step1Component.annotations = [
                            	new Component({
                            		selector: 'step1',
                            		templateUrl: 'app/wizard/step1/step1.template.html',
                            		styleUrls:  [],
                            		directives: [CountrySelectComponent, ValidateEmailDirective, ValidatePhoneDirective]
                            	})
];
