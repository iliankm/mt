import {Directive} from '@angular/core';
import {NG_VALIDATORS} from '@angular/forms';

export function validateEmail() {

	let EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;

	return (control) => {

		let value = control.value;

		if (!value) {return null}

		return EMAIL_REGEXP.test(value) ? null : {'validateEmail': value};
	}
}

export class ValidateEmailDirective {

	constructor() {

		this.valFn = validateEmail();
	}

	validate(control) {

		return this.valFn(control);
	}
}

ValidateEmailDirective.annotations = [
                                      	new Directive({
                                      		selector: '[validateEmail]',
                                      		providers: [{provide: NG_VALIDATORS, useExisting: ValidateEmailDirective, multi: true}]
                                      	})
                                      ];

