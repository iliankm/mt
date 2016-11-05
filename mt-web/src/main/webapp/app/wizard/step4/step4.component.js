import { Component } from '@angular/core';
import {MessagesService} from 'app/commons/services/messages/messages.service.js';

export class Step4Component {

	static get parameters() {

	    return [[MessagesService]];
	}

	constructor(messagesService) {

		this.RES = messagesService;
	}
}

Step4Component.annotations = [
                            	new Component({
                            		selector: 'step4',
                            		templateUrl: 'app/wizard/step4/step4.template.html',
                            		styleUrls:  [],
                            		directives: []
                            	})
];
