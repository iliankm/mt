import {Component, EventEmitter} from '@angular/core';
import {MessagesService} from 'app/commons/services/messages/messages.service';
import {UploadComponent} from 'app/commons/components/upload/upload.component';

export class Step3Component {

    static get parameters() {

        return [[MessagesService]];
    }

    constructor(messagesService) {

        //Resources service
	this.RES = messagesService;
        // employee id
        this.employeeId = null;
        // next event - fired when Next button is clicked
        this.next = new EventEmitter();
        // back event - fired when Previous button is clicked
        this.back = new EventEmitter();

    }

    /**
     * @return {string} - endpoint for upload employee images
     */
    getUrl() {

	return "resources/employees/" + this.employeeId + "/upload";
    }

    /**
     * @return {number} - max file size
     */
    getMaxFileSize() {

	return 10485760;
    }

    /**
     * Upload component change handler
     */
    onChange(event) {}

    /**
     * Upload component start handler
     */
    onStart(event) {}

    /**
     * Upload component progress handler
     */
    onProgress(event) {}

    /**
     * Upload component ready handler
     */
    onReady(event) {}

    /**
     * Upload component error handler
     */
    onError(event) {}

    /**
     * Continue button click handler
     */
    onNext() {

	this.next.emit();
    }

    /**
     * Back button click handler
     */
    onPrevious() {

        this.back.emit();
    }


}

Step3Component.annotations = [
                                new Component({
                                    selector: 'step3',
                                    templateUrl: 'app/wizard/step3/step3.template.html',
                                    styleUrls:  [],
                                    directives: [UploadComponent],
                                    outputs: ['next', 'back']
                                })
];
