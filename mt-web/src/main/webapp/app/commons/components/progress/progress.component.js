import {Component, ElementRef} from '@angular/core';

export class ProgressComponent {

    static get parameters() {

        return [[ElementRef]];
    }

    constructor(el) {

        // component's native element by DI
        this.el = el;

        // input: default value 0
        this.value_ = 0;
    }

    /**
     * getter for value property
     */
    get value() {
        return this.value_;
    }

    /**
     * setter for value property
     */
    set value(v) {

        if (!v) {
            this.value_ = 0;
        }
        else {
            if (v >= 0 && v <= 100) {
                this.value_ = v;
            }
            else {
                console.error("Invalid value: " + v);
            }
        }
    }

    /**
     * @return value percentage formatted text
     */
    valueText() {

        return this.value ? this.value + '%' : '';
    }
}

ProgressComponent.annotations = [
                                new Component({
                                    selector: 'progress-bar',
                                    templateUrl: 'app/commons/components/progress/progress.template.html',
                                    providers: [],
                                    styleUrls:  ['app/commons/components/progress/progress.component.css'],
                                    directives: [],
                                    inputs: ['value'],
                                    outputs: []
                                })
];
