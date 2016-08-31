import { Component } from '@angular/core';

export class Step1Component {

	constructor() {

		this.RES = MT.Resources;
	}
}

Step1Component.annotations = [
                            	new Component({
                            		selector: 'step1',
                            		templateUrl: 'app/wizard/step1/step1.template.html',
                            		styleUrls:  [],
                            		directives: []
                            	})
];
