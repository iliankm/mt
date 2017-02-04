import {Component, ViewChildren, forwardRef, EventEmitter} from '@angular/core';

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

export class WizardStepsComponent {

    constructor() {

        this.wizardSteps = [];
        this._current = new WizardStep("", "", "", false);
        this.select = new EventEmitter();
    }

    set current(current) {

        current.isVisited = true;
        this._current = current;
    }

    get current() {

        return this._current;
    }

    onSelect(wizardStep) {

        var me = this;

        if (wizardStep.isVisited && me.current.name !== wizardStep.name) {
            //find current step in the array and reference it
            let old = $.grep(me.wizardSteps, function(wz){return wz.name === me.current.name})[0];

            me.current = wizardStep;

            me.select.emit({
                'old': old,
                'new': wizardStep
            });
        }
    }

    getStepClasses(wizardStep) {

        let classes = {
            current: wizardStep.name == this.current.name,
            visited: wizardStep.isVisited,
            disabled: !wizardStep.isVisited,
            "bs-wizard-step": true
        }

        let stepSize = Math.floor(12 / this.wizardSteps.length);
        classes["col-" + stepSize] = true;

        return classes;
    }

}

WizardStepsComponent.annotations = [
                                new Component({
                                    selector: 'wizard-steps',
                                    templateUrl: 'app/wizard/wizard-steps/wizard-steps.template.html',
                                    styleUrls:  ['app/wizard/wizard-steps/wizard-steps.component.css'],
                                    directives: [],
                                    queries: {},
                                    inputs: ['wizardSteps', 'current'],
                                    outputs: ['select']
                                })
];
