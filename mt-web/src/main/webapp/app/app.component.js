import { Component } from '@angular/core';
import {Router, Routes, ROUTER_DIRECTIVES} from '@angular/router';
import {MessagesService} from 'app/commons/services/messages/messages.service.js';

export class AppComponent {

	static get parameters() {

	    return [[Router], [MessagesService]];
	}

	constructor(router, messagesService) {

		this.router = router;

		this.RES = messagesService;
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
