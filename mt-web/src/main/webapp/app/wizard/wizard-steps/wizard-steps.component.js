import { Component, ContentChildren, forwardRef } from '@angular/core';

import { WizardStepComponent } from 'app/wizard/wizard-steps/wizard-step.component';

export class WizardStepsComponent {

	constructor() {
		this._current = null;
	}

	set current(current) {
		this._current = current;
		this.updateCurrentStep(current);
	}

	get current() {
		return this._current;
	}

	updateCurrentStep(stepName) {
		if (this.stepComponents) {
			this.stepComponents.forEach(function(cmp){
				cmp.isCurrent = cmp.name && stepName && cmp.name === stepName;
				cmp.isVisited = cmp.isVisited || cmp.isCurrent;
			});
		}
	}

	ngAfterContentInit() {
		//debugger
		this.updateCurrentStep(this._current);
	}

}

WizardStepsComponent.annotations = [
                            	new Component({
                            		selector: 'wizard-steps',
                            		templateUrl: 'app/wizard/wizard-steps/wizard-steps.template.html',
                            		styleUrls:  ['app/wizard/wizard-steps/wizard-steps.component.css'],
                            		directives: [WizardStepComponent],
                            		queries: {stepComponents: new ContentChildren(forwardRef(() => WizardStepComponent))},
                            		inputs: ['current']
                            	})
];
