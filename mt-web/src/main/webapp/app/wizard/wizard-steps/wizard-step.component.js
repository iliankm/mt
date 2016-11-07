import { Component, EventEmitter } from '@angular/core';

/**
 * WizardStep model class
 */
export class WizardStep {

    constructor(name, title, info, isVisited) {

        this.name = name;
        this.title = title;
        this.info = info;
        this.isVisited = isVisited;
    }
}

export class WizardStepComponent {

    constructor() {

        this.isCurrent = false;
        this.wizardStep = new WizardStep("", "", "", false);;
        this.size = 1;
        this.select = new EventEmitter();
    }

    onClick() {

        this.select.emit(this.wizardStep);
    }

    setClasses() {

        return {
            current: this.isCurrent,
            visited: this.wizardStep.isVisited,
            disabled: !this.wizardStep.isVisited
        }
    }

}

WizardStepComponent.annotations = [
                                new Component({
                                    selector: 'wizard-step',
                                    templateUrl: 'app/wizard/wizard-steps/wizard-step.template.html',
                                    styleUrls:  ['app/wizard/wizard-steps/wizard-step.component.css'],
                                    inputs: ['wizardStep'],
                                    outputs: ['select']
                                })
];
