import {Component, EventEmitter} from '@angular/core';
import {MessagesService} from 'app/commons/services/messages/messages.service';
import {UploadComponent} from 'app/commons/components/upload/upload.component';
import {ImageFileComponent} from 'app/wizard/step3/image-file.component';
import {ImageFile} from 'app/wizard/step3/image-file.model';

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
        //array of uploading/uploaded ImageFile objects
        this.imageFiles = [];

    }

    /**
     * @return {string} - endpoint for upload employee images
     */
    getUrl() {

        return "resources/employees/" + this.employeeId + "/images";
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
    onStart(event) {

        let me = this;

    	let imageFile = new ImageFile({
    	    employeeId: me.employeeId,
    	    name: event.name,
    	    size: event.size
    	});

    	this.imageFiles.push(imageFile);
    }

    /**
     * Upload component progress handler
     */
    onProgress(event) {}

    /**
     * Upload component ready handler
     */
    onReady(event) {console.log(event)}

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
                                    directives: [UploadComponent, ImageFileComponent],
                                    outputs: ['next', 'back']
                                })
];
