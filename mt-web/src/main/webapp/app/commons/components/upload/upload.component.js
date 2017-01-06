import {Component, EventEmitter} from '@angular/core';

export const ERRORS = {
        maxFilesExceeded: {
            code: 1,
            message: "Number of files exceeds the maximum allowed."
        },
        maxSizeExceeded: {
            code: 2,
            message: "File(s) size exceeds the maximum allowed."
        },
        serverError: {
            code: 3,
            message: "Server error."
        }
}

export class UploadComponent {


    constructor() {
        // input: url
        this.url = '';
        // input: default label
        this.label = 'Browse';
        // input: default multiple false
        this.multiple = false;
        // input: default disabled false
        this.disabled = false;
        // input: default accept all
        this.accept = null;
        // input: default max size 1MB
        this.maxSize = 1048576;
        // input: default max files 1
        this.maxFiles = 1;
        // output: change event
        this.change = new EventEmitter();
        // output: start event
        this.start = new EventEmitter();
        // output: progress event
        this.progress = new EventEmitter();
        // output: ready event
        this.ready = new EventEmitter();
        // output: error event
        this.error = new EventEmitter();
    }

    onChange(event) {

        let files = event.target.files;

        if (files && files.length > 0) {

            //maxFiles check
            if (files.length > this.maxFiles) {
                console.error(ERRORS.maxFilesExceeded);
                this.error.emit(ERRORS.maxFilesExceeded);
                event.target.value = "";
                return;
            }

            //maxSize check
            for (let i = 0; i < files.length; i++) {
                let file = files[i];
                if (file.size > this.maxSize) {
                    console.error(ERRORS.maxSizeExceeded);
                    this.error.emit(ERRORS.maxSizeExceeded);
                    event.target.value = "";
                    return;
                }
            }

            //if no errors fire change event
            this.change.emit(files);

            //upload selected files...
            for (let i = 0; i < files.length; i++) {
                let file = files[i];
                //fire start event
                this.start.emit(file);
                //upload file
                this.uploadFile(file);
            }
        }
        else {
            this.change.emit();
        }
    }

    uploadFile(file) {

        let me = this;

        let xhr = new XMLHttpRequest();

        let fd = new FormData();

        xhr.open("POST", this.url, true);

        //progress listener
        xhr.upload.addEventListener("progress", function(e) {
            let pc = parseInt(e.loaded / e.total * 100);

            me.progress.emit({
                file: file,
                progress: pc
            });
        }, false);

        //onready listener
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    //fire ready event
                    me.ready.emit({
                        file: file,
                        responseText: xhr.responseText ? xhr.responseText : null
                    });
                }
                else {
                    let err = {
                        file: file,
                        code: ERRORS.serverError.code,
                        message: ERRORS.serverError.message,
                        responseText: xhr.responseText ? xhr.responseText : null
                    }

                    console.error(err);
                    //fire error event
                    me.error.emit(err);
                }
            }
        };

        fd.append("upload_file", file);

        xhr.send(fd);
    }
}

UploadComponent.annotations = [
    new Component({
        selector: 'upload',
        templateUrl: 'app/commons/components/upload/upload.template.html',
        providers: [],
        styleUrls:  ['app/commons/components/upload/upload.component.css'],
        directives: [],
        inputs: ['url', 'label', 'multiple', 'disabled', 'accept', 'maxSize', 'maxFiles'],
        outputs: ['change', 'start', 'progress', 'ready', 'error']
    })
];