import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

export function formatString() {

    // The string containing the format items (e.g. "{0}")
    // will and always has to be the first argument.
    let theString = arguments[0];

    // start with the second argument (i = 1)
    for (let i = 1; i < arguments.length; i++) {
        // "gm" = RegEx options for Global search (more than one instance)
        // and for Multiline search
        let regEx = new RegExp("\\{" + (i - 1) + "\\}", "gm");
        theString = theString.replace(regEx, arguments[i]);
    }

    return theString;
}

let MESSAGES = {};

export class MessagesService {

    constructor(http) {

        this.http = http;
    }

    static get parameters() {

        return [[Http]];
    }

    load() {

        return new Promise((resolve, reject) => {
            this.http.get('resources/messages').subscribe(res => {
                MESSAGES = res.json();
                resolve();
            });
        });
    }

    get(key) {

        return MESSAGES[key];
    }
}

MessagesService.annotations = [new Injectable()];