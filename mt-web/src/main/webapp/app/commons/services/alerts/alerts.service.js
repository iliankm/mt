import {Injectable} from '@angular/core';
import {MessagesService} from 'app/commons/services/messages/messages.service';

export class AlertsService {

    static get parameters() {

        return [[MessagesService]];
    }

    constructor(messagesService) {

        this.RES = messagesService;
    }

    /**
     * Register passed modal in instance property
     */
    registerModals(alertModal) {

        this.alertModal = alertModal;
    }

    /**
     * Show alert modal arranged as info dialog
     *
     * @param {string} message
     */
    info(message) {

        //set header title
        this.alertModal.modalDOMConteiner.getElementsByClassName('alert-title')[0].innerHTML = this.RES.get('commons.alert.info');
        //set header class
        this.alertModal.modalDOMConteiner.getElementsByClassName('alert')[0].className = 'alert alert-info';
        //set OK button
        this.alertModal.setButtons([{
            value: this.RES.get('commons.ok'),
            class: 'btn btn-primary',
            callback: m => m.hideModal()
        }]);
        //set message
        this.alertModal.modalDOMConteiner.getElementsByClassName('message')[0].innerHTML = message;
        //show
        this.alertModal.showModal();
    }

    /**
     * Show alert modal arranged as error dialog
     *
     * @param {string} message
     */
    error(message) {

        //set header title
        this.alertModal.modalDOMConteiner.getElementsByClassName('alert-title')[0].innerHTML = this.RES.get('commons.alert.error');
        //set header class
        this.alertModal.modalDOMConteiner.getElementsByClassName('alert')[0].className = 'alert alert-danger';
        //set OK button
        this.alertModal.setButtons([{
            value: this.RES.get('commons.ok'),
            class: 'btn btn-primary',
            callback: m => m.hideModal()
        }]);
        //set message
        this.alertModal.modalDOMConteiner.getElementsByClassName('message')[0].innerHTML = message;
        //show
        this.alertModal.showModal();
    }

    /**
     * Show alert modal arranged as confirmation dialog
     *
     * @param {string} message
     * @param {Object[]} buttons
     * @param {Object[]} buttons[].value - button caption value
     * @param {Object[]} buttons[].class - button class
     * @param {Object[]} buttons[].callback - button callback function
     */
    confirm(message, buttons) {

        //set header title
        this.alertModal.modalDOMConteiner.getElementsByClassName('alert-title')[0].innerHTML = this.RES.get('commons.alert.confirmation');
        //set header class
        this.alertModal.modalDOMConteiner.getElementsByClassName('alert')[0].className = 'alert alert-warning';
        //set buttons
        this.alertModal.setButtons(buttons ? buttons : []);
        //set message
        this.alertModal.modalDOMConteiner.getElementsByClassName('message')[0].innerHTML = message;
        //show
        this.alertModal.showModal();
    }

}

AlertsService.annotations = [new Injectable()];