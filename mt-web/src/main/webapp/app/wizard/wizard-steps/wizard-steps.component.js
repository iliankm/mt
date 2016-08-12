import { Component } from '@angular/core';

import { WizardStepComponent } from 'app/wizard/wizard-steps/wizard-step.component';

export class WizardStepsComponent {

	constructor() {
	}

}

WizardStepsComponent.annotations = [
                            	new Component({
                            		selector: 'wizard-steps',
                            		templateUrl: 'app/wizard/wizard-steps/wizard-steps.template.html',
                            		styleUrls:  ['app/wizard/wizard-steps/wizard-steps.component.css'],
                            		directives: [WizardStepComponent]
                            	})
];
