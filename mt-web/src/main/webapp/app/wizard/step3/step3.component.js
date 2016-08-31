import { Component } from '@angular/core';

export class Step3Component {

	constructor() {

		this.RES = MT.Resources;
	}
}

Step3Component.annotations = [
                            	new Component({
                            		selector: 'step3',
                            		templateUrl: 'app/wizard/step3/step3.template.html',
                            		styleUrls:  [],
                            		directives: []
                            	})
];
