import { Component } from '@angular/core';
import { CountrySelectComponent } from 'app/commons/components/country-select/country-select.component';

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
				}

		};
	}
}

Step1Component.annotations = [
                            	new Component({
                            		selector: 'step1',
                            		templateUrl: 'app/wizard/step1/step1.template.html',
                            		styleUrls:  [],
                            		directives: [CountrySelectComponent]
                            	})
];
