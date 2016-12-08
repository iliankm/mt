import { Component } from '@angular/core';
import {MessagesService} from 'app/commons/services/messages/messages.service';
import {UploadComponent} from 'app/commons/components/upload/upload.component';

export class Step3Component {

    static get parameters() {

        return [[MessagesService]];
    }

    constructor(messagesService) {

        this.RES = messagesService;
    }
}

Step3Component.annotations = [
                                new Component({
                                    selector: 'step3',
                                    templateUrl: 'app/wizard/step3/step3.template.html',
                                    styleUrls:  [],
                                    directives: [UploadComponent]
                                })
];
