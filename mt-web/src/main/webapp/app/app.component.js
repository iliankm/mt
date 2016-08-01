import { Component } from '@angular/core';
import {Routes, ROUTER_DIRECTIVES} from '@angular/router';

import { ListComponent } from 'app/list/list.component';
import { DetailsComponent } from 'app/details/details.component';
import { WizardComponent } from 'app/wizard/wizard.component';

export class AppComponent {

	constructor() {
	}

}

AppComponent.annotations = [
                            	new Component({
                            		selector: 'mt-app',
                            		templateUrl: 'app/app.template.html',
                            		styleUrls:  ['app/app.component.css'],
                            		directives: [ROUTER_DIRECTIVES],
                            		precompile: [ListComponent, DetailsComponent, WizardComponent]
                            	})
];
