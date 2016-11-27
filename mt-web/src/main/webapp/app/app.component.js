import {Component, ViewChild} from '@angular/core';
import {Router, Routes, ROUTER_DIRECTIVES} from '@angular/router';
import {MessagesService} from 'app/commons/services/messages/messages.service';
import {AlertsService} from 'app/commons/services/alerts/alerts.service';
import {ModalComponent} from 'app/commons/components/modal/modal.component';

export class AppComponent {

    static get parameters() {

        return [[Router], [MessagesService], [AlertsService]];
    }

    constructor(router, messagesService, alertsService) {

        this.router = router;

        this.RES = messagesService;

        this.alertsService = alertsService;
    }

    /**
     * ngOnInit lifecycle hook
     */
    ngOnInit() {

        this.alertsService.registerModals(this.alertModal);
    }

    navigateToWizard() {

        this.router.navigate(['/wizard'])
    }
}

AppComponent.annotations = [
                                new Component({
                                    selector: 'mt-app',
                                    templateUrl: 'app/app.template.html',
                                    styleUrls:  ['app/app.component.css'],
                                    directives: [ROUTER_DIRECTIVES, ModalComponent],
                                    queries: {alertModal: new ViewChild('alertModal')}
                                })
];
