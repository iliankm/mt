import { Component } from '@angular/core';
import { WizardStepsComponent } from 'app/wizard/wizard-steps/wizard-steps.component';
import { WizardStep, WizardStepComponent } from 'app/wizard/wizard-steps/wizard-step.component';

export class WizardComponent {

	constructor() {
		this.step1 = new WizardStep("step1", "Step 1", "Info info...", true);
		this.step2 = new WizardStep("step2", "Step 2", "Info info...", false);
		this.step3 = new WizardStep("step3", "Step 3", "Info info...", false);

		this.steps = [this.step1, this.step2, this.step3];

		this.currentStep = this.step1;
	}

	onSelectStep(event) {
		console.log(event);
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
