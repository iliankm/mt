import { Component } from '@angular/core';

export class ListComponent {

	constructor() {
	}

}

ListComponent.annotations = [
                            	new Component({
                            		selector: 'emp-list',
                            		templateUrl: 'app/list/list.template.html',
                            		styleUrls:  []
                            	})
];
