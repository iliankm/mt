import {Directive} from '@angular/core';
import {NG_VALIDATORS} from '@angular/forms';

export function validatePhone() {

    let PHONE_REGEXP = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;

    return (control) => {

        let value = control.value;

        if (!value) {return null}

        return PHONE_REGEXP.test(value) ? null : {'validatePhone': value};
    }
}

export class ValidatePhoneDirective {

    constructor() {

        this.valFn = validatePhone();
    }

    validate(control) {

        return this.valFn(control);
    }
}

ValidatePhoneDirective.annotations = [
                                        new Directive({
                                            selector: '[validatePhone]',
                                            providers: [{provide: NG_VALIDATORS, useExisting: ValidatePhoneDirective, multi: true}]
                                        })
                                      ];

