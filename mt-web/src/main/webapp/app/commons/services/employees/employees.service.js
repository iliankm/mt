import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {BaseService} from 'app/commons/services/base.service.js';
import {CreateUpdateEmployeeArgument} from 'app/commons/services/employees/create-update-employee.argument.js';
import 'rxjs/add/operator/catch';
import 'rxjs/Rx';

const ENDPOINT_EMPLOYEES = "resources/employees";

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
     * @param {CreateUpdateEmployeeArgument}
     *                createUpdateEmployeeArgument - creation argument
     * @return {Observable for string} - id of the created employee
     */
    create(createUpdateEmployeeArgument) {

        return this.http.post(ENDPOINT_EMPLOYEES, createUpdateEmployeeArgument.toJSON())
            .map(r => r.text())
            .catch(this.handleError);
    }

    /**
     * Update employee by argument
     *
     * @param {string} id - id of the updated employee
     * @param {CreateUpdateEmployeeArgument}
     *                createUpdateEmployeeArgument - update argument
     * @return {Observable for string} - id of the updated employee
     */
    update(id, createUpdateEmployeeArgument) {

        return this.http.put(ENDPOINT_EMPLOYEES + "/" + id, createUpdateEmployeeArgument.toJSON())
            .map(r => r.text())
            .catch(this.handleError);
    }

    /**
     * Update employee address by argument
     *
     * @param {string} id - id of the updated employee
     * @param {Address} address - update argument
     * @return {Observable for string} - id of the updated employee
     */
    updateAddress(id, address) {

        return this.http.put(ENDPOINT_EMPLOYEES + "/" + id + "/address", address.toJSON())
            .map(r => r.text())
            .catch(this.handleError);
    }

    /**
     * Update employee phones by argument
     *
     * @param {string} id - id of the updated employee
     * @param {Phone[]} phones - array of phones
     * @return {Observable for string} - id of the updated employee
     */
    updatePhones(id, phones) {

        let phonesJSON = phones ? phones.map(p => p.toJSON()) : [];

        return this.http.put(ENDPOINT_EMPLOYEES + "/" + id + "/phones", phonesJSON)
            .map(r => r.text())
            .catch(this.handleError);
    }
}

EmployeesService.annotations = [new Injectable()];