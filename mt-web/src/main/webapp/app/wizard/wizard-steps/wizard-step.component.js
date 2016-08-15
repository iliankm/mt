import { Component, EventEmitter } from '@angular/core';

export class WizardStepComponent {

	constructor() {
		this.isCurrent = false;
		this.isVisited = false;
		this.name = null;
		this.title = null;
		this.info = null;
		this.size = 1;
		this.select = new EventEmitter();
	}

	onClick() {
		if (!this.isCurrent && this.isVisited) {
			this.select.emit(this.name);
		}
	}
}

WizardStepComponent.annotations = [
                            	new Component({
                            		selector: 'wizard-step',
                            		templateUrl: 'app/wizard/wizard-steps/wizard-step.template.html',
                            		styleUrls:  ['app/wizard/wizard-steps/wizard-step.component.css'],
                            		inputs: ['name', 'isVisited', 'title', 'info'],
                            		outputs: ['select']
                            	})
];
