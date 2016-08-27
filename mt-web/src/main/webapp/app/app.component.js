import { Component } from '@angular/core';
import {Router, Routes, ROUTER_DIRECTIVES} from '@angular/router';

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
                            		directives: [ROUTER_DIRECTIVES]
                            	})
];
