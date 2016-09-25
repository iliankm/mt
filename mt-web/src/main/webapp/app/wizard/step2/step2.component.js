import { Component } from '@angular/core';
import {MessagesService} from 'app/commons/services/messages/messages.service.js';

export class Step2Component {

	static get parameters() {

	    return [[MessagesService]];
	}

	constructor(messagesService) {

		this.RES = messagesService;
	}
}

Step2Component.annotations = [
                            	new Component({
                            		selector: 'step2',
                            		templateUrl: 'app/wizard/step2/step2.template.html',
                            		styleUrls:  [],
                            		directives: []
                            	})
];
