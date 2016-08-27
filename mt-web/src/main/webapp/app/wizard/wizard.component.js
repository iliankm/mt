import { Component } from '@angular/core';
import { WizardStepsComponent } from 'app/wizard/wizard-steps/wizard-steps.component';
import { WizardStep, WizardStepComponent } from 'app/wizard/wizard-steps/wizard-step.component';

export class WizardComponent {

	constructor() {

		this.RES = MT.Resources;

		this.step1 = new WizardStep("step1", MT.Resources.get('main.wizard.steps.step1.title'), MT.Resources.get('main.wizard.steps.step1.info'), true);
		this.step2 = new WizardStep("step2", MT.Resources.get('main.wizard.steps.step2.title'), MT.Resources.get('main.wizard.steps.step2.info'), true);
		this.step3 = new WizardStep("step3", MT.Resources.get('main.wizard.steps.step3.title'), MT.Resources.get('main.wizard.steps.step3.info'), false);

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
