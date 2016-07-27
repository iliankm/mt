import { Component } from '@angular/core';

export class DetailsComponent {

	constructor() {
	}

}

DetailsComponent.annotations = [
                            	new Component({
                            		selector: 'emp-details',
                            		templateUrl: 'app/details/details.template.html',
                            		styleUrls:  []
                            	})
];
