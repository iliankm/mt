import { Component } from '@angular/core';
import { WizardStepsComponent } from 'app/wizard/wizard-steps/wizard-steps.component';
import { WizardStepComponent } from 'app/wizard/wizard-steps/wizard-step.component';

export class WizardComponent {

	constructor() {
	}

}

WizardComponent.annotations = [
                            	new Component({
                            		selector: 'emp-wizard',
                            		templateUrl: 'app/wizard/wizard.template.html',
                            		styleUrls:  [],
                            		directives: [WizardStepsComponent, WizardStepComponent]
                            	})
];
