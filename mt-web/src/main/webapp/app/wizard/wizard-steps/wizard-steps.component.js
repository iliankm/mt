import { Component, ViewChildren, forwardRef, EventEmitter } from '@angular/core';

import { WizardStep, WizardStepComponent } from 'app/wizard/wizard-steps/wizard-step.component';

export class WizardStepsComponent {

	constructor() {
		this.wizardSteps = [];
		this._current = null;
		this.select = new EventEmitter();
	}

	set current(current) {
		this._current = current;
		this._updateCurrentStep(current);
		current.isVisited = true;
	}

	get current() {
		return this._current;
	}

	_updateCurrentStep(wizardStep) {
		if (this.stepComponents) {
			this.stepComponents.forEach(function(cmp){
				cmp.isCurrent = cmp.wizardStep.name && wizardStep.name && cmp.wizardStep.name === wizardStep.name;
			});
		}
	}

	ngAfterViewInit() {
		//update current step
		this._updateCurrentStep(this._current);

		//calculate and update each step size property
		if (this.stepComponents && this.stepComponents.length > 0) {
			let size = Math.floor(12/this.stepComponents.length);
			size = size >= 1 ? size : 1;

			this.stepComponents.forEach(function(cmp){
				cmp.size = size;
			});
		}
	}

	onSelect(wizardStep) {
		if (wizardStep.isVisited && this.current.name !== wizardStep.name) {
			this.current = wizardStep;
			this.select.emit(wizardStep);
		}
	}
}

WizardStepsComponent.annotations = [
                            	new Component({
                            		selector: 'wizard-steps',
                            		templateUrl: 'app/wizard/wizard-steps/wizard-steps.template.html',
                            		styleUrls:  ['app/wizard/wizard-steps/wizard-steps.component.css'],
                            		directives: [WizardStepComponent],
                            		queries: {stepComponents: new ViewChildren(WizardStepComponent)},
                            		inputs: ['wizardSteps', 'current'],
                            		outputs: ['select']
                            	})
];
