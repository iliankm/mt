import {Component, EventEmitter} from '@angular/core';

export class UploadComponent {


    constructor() {

	//input: default label
	this.label = 'Browse';
	//input: default multiple false
	this.multiple = false;
	//input: default disabled false
	this.disabled = false;
	//input: default accept images
	this.accept = 'image/*';
	//input: default max size 1MB
	this.maxSize = 1048576;
	//input: default max files 1
	this.maxFiles = 1;
	//output: change event
	this.change = new EventEmitter();
	//output: start event
	this.start = new EventEmitter();
	//output: progress event
	this.progress = new EventEmitter();
	//output: ready event
	this.ready = new EventEmitter();
	//output: error event
	this.error = new EventEmitter();
    }
}

UploadComponent.annotations = [
    new Component({
        selector: 'upload',
        templateUrl: 'app/commons/components/upload/upload.template.html',
        providers: [],
        styleUrls:  [],
        directives: [],
        inputs: ['label', 'multiple', 'disabled', 'accept', 'maxSize', 'maxFiles'],
        outputs: ['change', 'start', 'progress', 'ready', 'error']
    })
];