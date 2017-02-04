import {Component, ElementRef, EventEmitter} from '@angular/core';

export const SIZE = {
        small: 'small',
        normal: 'normal',
        large: 'large'
};

export class ModalComponent {

    static get parameters() {

        return [[ElementRef]];
    }

    constructor(el) {

        //component's native element by DI
        this.el = el;
        //instance size property - for access from template
        this.SIZE = SIZE;
        //input: component default size normal
        this.size = SIZE.normal;
        //output: show event
        this.show = new EventEmitter();
        //output: shown event
        this.shown = new EventEmitter();
        //output: hide event
        this.hide = new EventEmitter();
        //output: hidden event
        this.hidden = new EventEmitter();
        //buttons array
        this.buttons = [];
    }

    /**
     * ngOnInit lifecycle hook
     */
    ngOnInit() {

        let me = this;

       this.modalDOMConteiner = this.el.nativeElement.firstChild;

        //initialize the modal for component's native el
        $(this.modalDOMConteiner).modal({
            backdrop: true,
            keyboard: true,
            focus: true,
            show: false
        });

        //show event
        $(this.modalDOMConteiner).on('show.bs.modal', function(e) {me.show.emit()});
        //shown event
        $(this.modalDOMConteiner).on('shown.bs.modal', function(e) {me.shown.emit()});
        //hide event
        $(this.modalDOMConteiner).on('hide.bs.modal', function(e) {me.hide.emit()});
        //hidden event
        $(this.modalDOMConteiner).on('hidden.bs.modal', function(e) {me.hidden.emit()});

    }

    /**
     * Set buttons array.
     * Buttons should be rendered in the footer of the modal.
     *
     * @param {Object[]} buttons
     * @param {Object[]} buttons[].value - button caption value
     * @param {Object[]} buttons[].class - button class
     * @param {Object[]} buttons[].callback - button callback function
     */
    setButtons(buttons) {

        this.buttons = buttons;
    }

    /**
     * Button click event.
     * The button is one of those rendered via setButtons method.
     */
    onBtnClick(btn) {

        if (btn.callback) {
            btn.callback(this);
        }
    }

    /**
     * Show the modal
     */
    showModal() {

        $(this.modalDOMConteiner).modal('show');
    }

    /**
     * Hide the modal
     */
    hideModal() {

        $(this.modalDOMConteiner).modal('hide');
    }

}

ModalComponent.annotations = [
                                new Component({
                                    selector: 'modal',
                                    templateUrl: 'app/commons/components/modal/modal.template.html',
                                    providers: [],
                                    styleUrls:  ['app/commons/components/modal/modal.component.css'],
                                    directives: [],
                                    inputs: ['size'],
                                    outputs: ['show', 'shown', 'hide', 'hidden']
                                })
];
