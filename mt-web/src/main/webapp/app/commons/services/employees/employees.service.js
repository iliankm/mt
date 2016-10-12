import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

export class EmployeesService {

	constructor(http) {

		this.http = http;
	}

	static get parameters() {

	    return [[Http]];
	}

}

EmployeesService.annotations = [new Injectable()];