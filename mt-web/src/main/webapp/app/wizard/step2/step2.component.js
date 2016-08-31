import { Component } from '@angular/core';

export class Step2Component {

	constructor() {

		this.RES = MT.Resources;
	}
}

Step2Component.annotations = [
                            	new Component({
                            		selector: 'step2',
                            		templateUrl: 'app/wizard/step2/step2.template.html',
                            		styleUrls:  [],
                            		directives: []
                            	})
];
