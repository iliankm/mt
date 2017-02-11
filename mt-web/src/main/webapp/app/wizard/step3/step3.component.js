import {Component, EventEmitter} from '@angular/core';
import {MessagesService} from 'app/commons/services/messages/messages.service';
import {UploadComponent} from 'app/commons/components/upload/upload.component';
import {ProgressComponent} from 'app/commons/components/progress/progress.component';
import {ImageFile} from 'app/wizard/step3/image-file.model';

export class Step3Component {

    static get parameters() {

        return [[MessagesService]];
    }

    constructor(messagesService) {

        // Resources service
        this.RES = messagesService;
        // employee id
        this.employeeId = null;
        // next event - fired when Next button is clicked
        this.next = new EventEmitter();
        // back event - fired when Previous button is clicked
        this.back = new EventEmitter();
        // array of uploading/uploaded ImageFile objects
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

        if (this.getImageFile(event.name)) {
            console.error('File: ' + event.name + ' already selected.');
            return;
        }

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
    onProgress(event) {

        let imageFile = this.getImageFile(event.file.name);

        if (imageFile) {
            imageFile.progress = event.progress;
        }
    }

    /**
     * Upload component ready handler
     */
    onReady(event) {

        let imageFile = this.getImageFile(event.file.name);

        if (imageFile) {
            imageFile.serverName = event.responseText;
        }
    }

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

    /**
     * Locate ImageFile object by file name
     */
    getImageFile(fileName) {

        return this.imageFiles.find(el => fileName && el.name && el.name === fileName);
    }


}

Step3Component.annotations = [
                                new Component({
                                    selector: 'step3',
                                    templateUrl: 'app/wizard/step3/step3.template.html',
                                    styleUrls:  ['app/wizard/step3/step3.component.css'],
                                    directives: [UploadComponent, ProgressComponent],
                                    outputs: ['next', 'back']
                                })
];
