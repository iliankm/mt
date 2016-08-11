import { Component } from '@angular/core';

export class WizardStepsComponent {

	constructor() {
	}

}

WizardStepsComponent.annotations = [
                            	new Component({
                            		selector: 'wizard-steps',
                            		templateUrl: 'app/wizard/wizard-steps/wizard-steps.template.html',
                            		styleUrls:  ['app/wizard/wizard-steps/wizard-steps.component.css']
                            	})
];
