import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule}   from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

import {CommonsModule} from 'app/commons/commons.module';

import {WizardComponent} from 'app/wizard/wizard.component';
import {WizardStepsComponent} from 'app/wizard/wizard-steps/wizard-steps.component';
import {WizardStepComponent} from 'app/wizard/wizard-steps/wizard-step.component';
import {Step1Component} from 'app/wizard/step1/step1.component';
import {Step2Component} from 'app/wizard/step2/step2.component';
import {Step3Component} from 'app/wizard/step3/step3.component';
import {Step4Component} from 'app/wizard/step4/step4.component';

export class WizardModule {

}

WizardModule.annotations = [
                        	new NgModule({

                        		imports: [FormsModule, BrowserModule, CommonsModule],

                        		declarations: [
                        		               	WizardComponent,
                  		               			WizardStepComponent,
                   		               			WizardStepsComponent,
                       		               		Step1Component,
                       		               		Step2Component,
                       		               		Step3Component,
                       		               		Step4Component
                        		              ],

                          		exports: [
                          		          	WizardComponent,
               		               			WizardStepComponent,
               		               			WizardStepsComponent,
                   		               		Step1Component,
                   		               		Step2Component,
                   		               		Step3Component,
                   		               		Step4Component
                   		               	  ],

                        		providers: []

                        	})
                        ];
