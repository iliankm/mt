import {Component,EventEmitter, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {MessagesService} from 'app/commons/services/messages/messages.service.js';
import {EmployeesService} from 'app/commons/services/employees/employees.service.js';
import {PHONE_TYPES, Phone} from 'app/commons/services/employees/phone.model.js';
import {Address} from 'app/commons/services/employees/address.model.js';
import {CountrySelectComponent} from 'app/commons/components/country-select/country-select.component';
import {ValidatePhoneDirective} from 'app/commons/directives/validate-phone/validate-phone.directive';

export class Step2Component {

    static get parameters() {

        return [[Router], [MessagesService], [EmployeesService]];
    }

    constructor(router, messagesService, employeesService) {

        // Router from DI
        this.router = router;
        // MessageService from DI
        this.RES = messagesService;
        // EmployeesService from DI
        this.employeesService = employeesService;
        // phone types
        this.PHONE_TYPES = PHONE_TYPES;
        // added phones
        this.phones = [];
        // next event - fired when Next button is clisked and data is
        // successfully saved
        this.next = new EventEmitter();
        // back event - fired when Previous button is clicked
        this.back = new EventEmitter();

        // employee id
        this.employeeId = null;
    }

    onPhoneFormSubmit(form) {

        this.phones.push({phone: form.value.phoneNumber, type: form.value.phoneType});
        form.reset();
    }

    onDeletePhone(ind) {

        this.phones.splice(ind, 1);
    }

    onPrevious() {

        this.back.emit();
    }

    onNext() {

        this.update().then(
                r => {this.next.emit()},
                err => {});
    }

    /**
     * Validate data then update employee address.
     *
     * @return {Promise} - the Promise is resolved if data is updated
     *         successfully and rejected if validation failed or some server
     *         error occured.
     */
    update() {

        let me = this;

        return new Promise((resolve, reject) => {

            if (this.validate()) {

                // create Address
                let address = new Address({
                    street: this.addressForm.value.street,
                    zip: this.addressForm.value.zip,
                    city: this.addressForm.value.city,
                    country: this.addressForm.value.country
                });

                // update employee address
                this.employeesService.updateAddress(this.employeeId, address)
                    .subscribe(
                            id => {resolve()},
                            err => {reject()});
            } else {
                reject();
            }
        });
    }

    /**
     * Validate step 2 data
     *
     * @return {boolean} true if data is valid, false otherwise
     */
    validate() {

        $.each(this.addressForm.controls, (k, v) => {
            if (v.markAsTouched) {v.markAsTouched()}
        });

        return this.addressForm.valid;
    }
}

Step2Component.annotations = [
                                new Component({
                                    selector: 'step2',
                                    templateUrl: 'app/wizard/step2/step2.template.html',
                                    styleUrls:  [],
                                    directives: [CountrySelectComponent, ValidatePhoneDirective],
                                    queries: {addressForm: new ViewChild('addressForm')},
                                    outputs: ['next', 'back']
                                })
];
