import {Component, EventEmitter} from '@angular/core';
import {ImageFile} from 'app/wizard/step3/image-file.model';

export class ImageFileComponent {

    constructor() {

        //input: ImageFile object
        this.imageFile = null;
    }

}

ImageFileComponent.annotations = [
                              new Component({
                                  selector: 'image-file',
                                  templateUrl: 'app/wizard/step3/image-file.template.html',
                                  styleUrls:  [],
                                  directives: [],
                                  inputs: ['imageFile'],
                                  outputs: []
                              })
];