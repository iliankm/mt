import { Component } from '@angular/core';

export class AppComponent {

	constructor() {
	}

}

AppComponent.annotations = [
                            	new Component({
                            		selector: 'mt-app',
                            		template: '<h1>Angular 2 App...</h1>'
                            	})
];
