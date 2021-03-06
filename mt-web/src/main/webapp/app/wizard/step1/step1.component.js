import {Component, EventEmitter, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {ValidateEmailDirective} from 'app/commons/directives/validate-email/validate-email.directive';
import {MessagesService} from 'app/commons/services/messages/messages.service';
import {EmployeesService} from 'app/commons/services/employees/employees.service';
import {AlertsService} from 'app/commons/services/alerts/alerts.service';
import {CreateUpdateEmployeeArgument} from 'app/commons/services/employees/create-update-employee.argument';
import {GENDERS} from 'app/commons/services/employees/employee.model';

export class Step1Component {

    static get parameters() {

        return [[Router], [MessagesService], [EmployeesService], [AlertsService]];
    }

    constructor(router, messagesService, employeesService, alertsService) {

        // Router from DI
        this.router = router;
        // MessageService from DI
        this.RES = messagesService;
        // EmployeesService from DI
        this.employeesService = employeesService;
        // AlertsService from DI
        this.alertsService = alertsService;
        // gender types
        this.GENDERS = GENDERS;
        // created event - fired when employee is successfully created
        this.created = new EventEmitter();
        // next event - fired when Next is clisked and data is successfully
        this.next = new EventEmitter();
        // isSaving flag
        this.isSaving = false;

        // employee id
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

        me.isSaving = true;

        this.createOrUpdate().subscribe(
                id => {
                    if ($.isEmptyObject(me.employeeId)) {
                        me.employeeId = id;
                        me.created.emit({id: id});
                    }

                    me.next.emit();

                    me.isSaving = false;
                },
                err => {
                    this.alertsService.error(err);
                    me.isSaving = false;
                });
    }

    /**
     * Validate data then create (or update) employee.
     * is saved with success.
     *
     * @return {Observable for string} - employee id
     */
    createOrUpdate() {

        if (this.validate()) {

            // create CreateUpdateEmployeeArgument
            let createUpdateEmployeeArgument = new CreateUpdateEmployeeArgument({
                identificationNumber: this.employeeForm.value.idnum,
                name: this.employeeForm.value.name,
                gender: this.employeeForm.value.gender,
                email: this.employeeForm.value.email
            });

            if ($.isEmptyObject(this.employeeId)) {
                // create employee
                return this.employeesService.create(createUpdateEmployeeArgument);

            } else {
                // update employee
                return this.employeesService.update(this.employeeId, createUpdateEmployeeArgument);
            }
        } else {
            return Observable.throw(this.RES.get('commons.validation.errors'));
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
                                    directives: [ValidateEmailDirective],
                                    queries: {employeeForm: new ViewChild('employeeForm')},
                                    outputs: ['created', 'next']
                                })
];
