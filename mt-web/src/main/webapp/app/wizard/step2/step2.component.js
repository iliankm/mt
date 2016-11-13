import {Component,EventEmitter, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
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
        // isSaving flag
        this.isSaving = false;

        // employee id
        this.employeeId = null;
    }

    onPhoneFormSubmit(form) {

        let me = this;

        let phoneNumber = form.value.phoneNumber;
        let phoneType = form.value.phoneType;

        //if already added - alert and quit
        if (this.phones.some(p => p.phone === phoneNumber && p.type === phoneType)) {
            alert(this.RES.get('wizard.employee.phones.phone_added'));
        }
        else {
            //create array of Phone objects
            let phones = this.phones.map(p => new Phone(p));
            //push the added phone
            phones.push(new Phone({phone: phoneNumber, type: phoneType}));

            //update phones
            this.employeesService.updatePhones(this.employeeId, phones).subscribe(
                    r => {
                        me.phones.push({phone: phoneNumber, type: phoneType});
                        form.reset();
                    },
                    err => {alert(err)});
        }
    }

    onDeletePhone(ind) {

        let me = this;

        //create array of Phone objects
        let phones = this.phones.map(p => new Phone(p));
        //remove the deleted phone from the array
        phones.splice(ind, 1);

        //update phones
        this.employeesService.updatePhones(this.employeeId, phones).subscribe(
                r => {
                    me.phones.splice(ind, 1);
                },
                err => {alert(err)});
    }

    onPrevious() {

        this.back.emit();
    }

    onNext() {

        let me = this;

        me.isSaving = true;

        this.update().subscribe(
                r => {
                    me.next.emit();
                    me.isSaving = false;
                },
                err => {
                    alert(err);
                    me.isSaving = false;
                });
    }

    /**
     * Validate data then update employee address.
     *
     * @return {Observable}
     */
    update() {

        if (this.validate()) {

            // create Address
            let address = new Address({
                street: this.addressForm.value.street,
                zip: this.addressForm.value.zip,
                city: this.addressForm.value.city,
                country: this.addressForm.value.country
            });

            // update employee address
            return this.employeesService.updateAddress(this.employeeId, address);
        } else {
            return Observable.throw(this.RES.get('commons.validation.errors'));
        }
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
