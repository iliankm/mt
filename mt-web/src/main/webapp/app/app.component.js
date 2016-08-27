import { Component } from '@angular/core';
import {Router, Routes, ROUTER_DIRECTIVES} from '@angular/router';

import { PageNotFoundComponent } from 'app/page-not-found/page-not-found.component';
import { ListComponent } from 'app/list/list.component';
import { DetailsComponent } from 'app/details/details.component';
import { WizardComponent } from 'app/wizard/wizard.component';

export class AppComponent {

	constructor(router) {

		this.router = router;
		this.RES = MT.Resources;
	}

	static get parameters() {

	    return [[Router]];
	}

	navigateToWizard() {
		this.router.navigate(['/wizard'])
	}

}

AppComponent.annotations = [
                            	new Component({
                            		selector: 'mt-app',
                            		templateUrl: 'app/app.template.html',
                            		styleUrls:  ['app/app.component.css'],
                            		directives: [ROUTER_DIRECTIVES],
                            		precompile: [PageNotFoundComponent, ListComponent, DetailsComponent, WizardComponent]
                            	})
];
