import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {BaseService} from 'app/commons/services/base.service.js';
import {CreateEmployeeArgument} from 'app/commons/services/employees/create-employee.argument.js';
import 'rxjs/add/operator/catch';

const ENDPOINT_EMPLOYEES = "employees";

export class EmployeesService extends BaseService {

	constructor(http) {

		super();

		this.http = http;
	}

	static get parameters() {

	    return [[Http]];
	}

	/**
	 * Create employee by creation argument
	 *
	 * @param {CreateEmployeeArgument} createEmployeeArgument - creation argument
	 * @return {Observable for String} - id of the created employee
	 */
	create(createEmployeeArgument) {

		return this.http.post(ENDPOINT_EMPLOYEES, createEmployeeArgument.toJSON())
				.map(r => r.json())
				.catch(this.handleError);
	}

}

EmployeesService.annotations = [new Injectable()];