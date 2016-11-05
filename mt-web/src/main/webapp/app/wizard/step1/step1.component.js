import {Component, EventEmitter, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {ValidateEmailDirective} from 'app/commons/directives/validate-email/validate-email.directive';
import {MessagesService} from 'app/commons/services/messages/messages.service.js';
import {EmployeesService} from 'app/commons/services/employees/employees.service.js';
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
	    // gender types
	    this.GENDERS = GENDERS;
	    // save event - fired when data is successfully saved
	    this.save = new EventEmitter();
	    // next event - fired when Next is clisked and data is successfully saved
	    this.next = new EventEmitter();

	    //employee id
	    this.employeeId = null;
	}

	ngAfterViewInit() {
	    // initialize tooltips
	    $('[data-toggle="tooltip"]').tooltip();
	}

	onCancel() {

	    this.router.navigate(['/list/all'])
	}

	onNext() {

		let me = this;

	    this.createOrUpdate().then(id => {
	    	me.next.emit();
	    });
	}

	/**
	 * Validate data then create (or update) employee. Fire 'save' event if
	 * data is saved with success.
	 *
	 * @return {Promise for string} - id of the created employee
	 */
	createOrUpdate() {

	    let me = this;

	    return new Promise((resolve, reject) => {

		    if (me.validate()) {

				//create CreateUpdateEmployeeArgument
				let createUpdateEmployeeArgument = new CreateUpdateEmployeeArgument({
					identificationNumber: me.employeeForm.value.idnum,
					name: me.employeeForm.value.name,
				    gender: me.employeeForm.value.gender,
				    email: me.employeeForm.value.email
				});

				if ($.isEmptyObject(me.employeeId)) {
				    //create employee
				    me.employeesService.create(createUpdateEmployeeArgument).subscribe(id => {
				    	me.employeeId = id;
				    	me.save.emit({id: id});
				    	resolve(id);
				    });
				} else {
				    //update employee
				    me.employeesService.update(me.employeeId, createUpdateEmployeeArgument).subscribe(id => {
				    	me.save.emit({id: id});
				    	resolve(id);
				    });
				}
		    }
	    });
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
                            		directives: [ValidateEmailDirective],
                            		queries: {employeeForm: new ViewChild('employeeForm')},
                            		outputs: ['save', 'next']
                            	})
];
