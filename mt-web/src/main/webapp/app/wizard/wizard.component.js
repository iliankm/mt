import {Component, ViewChild} from '@angular/core';
import {WizardStep, WizardStepsComponent} from 'app/wizard/wizard-steps/wizard-steps.component';
import {Step1Component} from 'app/wizard/step1/step1.component';
import {Step2Component} from 'app/wizard/step2/step2.component';
import {Step3Component} from 'app/wizard/step3/step3.component';
import {Step4Component} from 'app/wizard/step4/step4.component';
import {MessagesService} from 'app/commons/services/messages/messages.service.js';

export class WizardComponent {

    static get parameters() {

        return [[MessagesService]];
    }

    constructor(messagesService) {

        this.RES = messagesService;

        this.step1 = new WizardStep("step1", this.RES.get('wizard.steps.step1.title'), this.RES.get('wizard.steps.step1.info'), true);
        this.step2 = new WizardStep("step2", this.RES.get('wizard.steps.step2.title'), this.RES.get('wizard.steps.step2.info'), false);
        this.step3 = new WizardStep("step3", this.RES.get('wizard.steps.step3.title'), this.RES.get('wizard.steps.step3.info'), false);
        this.step4 = new WizardStep("step4", this.RES.get('wizard.steps.step4.title'), this.RES.get('wizard.steps.step4.info'), false);

        this.steps = [this.step1, this.step2, this.step3, this.step4];

        this.currentStep = this.step1;
    }

    onSelectStep(event) {

        this.currentStep = event['new'];
    }

    onEmployeeCreated(event) {

        if (event['id']) {
            this.step2Component.employeeId = event['id'];
            this.step3Component.employeeId = event['id'];
            this.step4Component.employeeId = event['id'];
        }
    }

    onStep1Next() {

        if (!this.step2.isVisited) {
            this.step2.isVisited = true;
        }

        this.currentStep = this.step2;
    }

    onStep2Next() {

        if (!this.step3.isVisited) {
            this.step3.isVisited = true;
        }

        this.currentStep = this.step3;
    }

    onStep2Back() {

        this.currentStep = this.step1;
    }

    onStep3Next() {

        if (!this.step4.isVisited) {
            this.step4.isVisited = true;
        }

        this.currentStep = this.step4;
    }

    onStep3Back() {

        this.currentStep = this.step2;
    }

}

WizardComponent.annotations = [
                                new Component({
                                    selector: 'emp-wizard',
                                    templateUrl: 'app/wizard/wizard.template.html',
                                    styleUrls:  [],
                                    directives: [WizardStepsComponent, Step1Component, Step2Component, Step3Component, Step4Component],
                                    queries: {
                                                step1Component: new ViewChild(Step1Component),
                                                step2Component: new ViewChild(Step2Component),
                                                step3Component: new ViewChild(Step3Component),
                                                step4Component: new ViewChild(Step4Component)
                                            }
                                })
];
