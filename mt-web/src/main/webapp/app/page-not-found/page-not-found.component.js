import { Component } from '@angular/core';

export class PageNotFoundComponent {

    constructor() {
    }

}

PageNotFoundComponent.annotations = [
                                new Component({
                                    selector: 'page-not-found',
                                    templateUrl: 'app/page-not-found/page-not-found.template.html',
                                    styleUrls:  []
                                })
];
