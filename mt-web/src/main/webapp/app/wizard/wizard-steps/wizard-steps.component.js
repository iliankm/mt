import { Component, ViewChildren, forwardRef, EventEmitter } from '@angular/core';

import { WizardStep, WizardStepComponent } from 'app/wizard/wizard-steps/wizard-step.component';

export class WizardStepsComponent {

    constructor() {

        this.wizardSteps = [];
        this._current = new WizardStep("", "", "", false);
        this.select = new EventEmitter();
        this.size = 25;
    }

    set current(current) {

        current.isVisited = true;
        this._current = current;
        this._updateCurrentStep(current);
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

        setTimeout(()=>{
            //update current step
            this._updateCurrentStep(this._current);

            //calculate and update each step size property
            if (this.stepComponents && this.stepComponents.length > 0) {
                let size = Math.floor(100/this.stepComponents.length);

                this.stepComponents.forEach(function(cmp){
                    cmp.size = size;
                });
            }
        });
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
