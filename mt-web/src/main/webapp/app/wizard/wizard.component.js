import {Component} from '@angular/core';
import {WizardStepsComponent} from 'app/wizard/wizard-steps/wizard-steps.component';
import {WizardStep, WizardStepComponent} from 'app/wizard/wizard-steps/wizard-step.component';
import {Step1Component} from 'app/wizard/step1/step1.component';
import {Step2Component} from 'app/wizard/step2/step2.component';
import {Step3Component} from 'app/wizard/step3/step3.component';
import {MessagesService} from 'app/commons/services/messages/messages.service.js';

export class WizardComponent {

	static get parameters() {

	    return [[MessagesService]];
	}

	constructor(messagesService) {

		this.RES = messagesService;

		this.step1 = new WizardStep("step1", this.RES.get('wizard.steps.step1.title'), this.RES.get('wizard.steps.step1.info'), true);
		this.step2 = new WizardStep("step2", this.RES.get('wizard.steps.step2.title'), this.RES.get('wizard.steps.step2.info'), true);
		this.step3 = new WizardStep("step3", this.RES.get('wizard.steps.step3.title'), this.RES.get('wizard.steps.step3.info'), true);

		this.steps = [this.step1, this.step2, this.step3];

		this.currentStep = this.step1;
	}

	onSelectStep(event) {
		this.currentStep = event['new'];
	}

}

WizardComponent.annotations = [
                            	new Component({
                            		selector: 'emp-wizard',
                            		templateUrl: 'app/wizard/wizard.template.html',
                            		styleUrls:  [],
                            		directives: [WizardStepsComponent, WizardStepComponent, Step1Component, Step2Component, Step3Component]
                            	})
];
