import {Component, EventEmitter, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {CountrySelectComponent} from 'app/commons/components/country-select/country-select.component';
import {ValidateEmailDirective} from 'app/commons/directives/validate-email/validate-email.directive';
import {ValidatePhoneDirective} from 'app/commons/directives/validate-phone/validate-phone.directive';
import {MessagesService} from 'app/commons/services/messages/messages.service.js';
import {EmployeesService} from 'app/commons/services/employees/employees.service.js';
import {PHONE_TYPES, Phone} from 'app/commons/services/employees/phone.model.js';
import {Address} from 'app/commons/services/employees/address.model.js';
import {CreateUpdateEmployeeArgument} from 'app/commons/services/employees/create-update-employee.argument.js';
import {GENDERS} from 'app/commons/services/employees/employee.model.js';

export class Step1Component {

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
	    // gender types
	    this.GENDERS = GENDERS;
	    // added phones
	    this.phones = [];
	    // save event - fired when data is successfully saved
	    this.save = new EventEmitter();
	}

	ngAfterViewInit() {
	    // initialize tooltips
	    $('[data-toggle="tooltip"]').tooltip();
	}

	onPhoneFormSubmit(form) {

	    this.phones.push({phone: form.value.phoneNumber, type: form.value.phoneType});
	    form.reset();
	}

	onDeletePhone(ind) {

	    this.phones.splice(ind, 1);
	}

	onCancel() {

	    this.router.navigate(['/list/all'])
	}

	onNext() {

	    this.createOrUpdate();
	}

	/**
	 * Validate data then create (or update) employee. Fire 'save' event if
	 * data is saved with success.
	 */
	createOrUpdate() {

	    if (this.validate()) {

		let createUpdateEmployeeArgument = new CreateUpdateEmployeeArgument({
		    name: this.employeeForm.value.name,
		    gender: this.employeeForm.value.gender,
		    address: {
			street: this.employeeForm.value.street,
			zip: this.employeeForm.value.zip,
			city: this.employeeForm.value.city,
			country: this.employeeForm.value.country
		    },
		    email: this.employeeForm.value.email,
		    phones: this.phones
		});

		console.log(createUpdateEmployeeArgument);

		this.save.emit({});
	    }
	}

	/**
	 * Validate step 1 data
	 *
	 * @return {boolean} true if data is valid, false otherwise
	 */
	validate() {

	    $.each(this.employeeForm.controls, (k, v) => {
		if (v.markAsTouched) {v.markAsTouched()}
	    });

	    return this.employeeForm.valid;
	}
}

Step1Component.annotations = [
                            	new Component({
                            		selector: 'step1',
                            		templateUrl: 'app/wizard/step1/step1.template.html',
                            		styleUrls:  [],
                            		directives: [CountrySelectComponent, ValidateEmailDirective, ValidatePhoneDirective],
                            		queries: {employeeForm: new ViewChild('employeeForm')},
                            		outputs: ['save']
                            	})
];
