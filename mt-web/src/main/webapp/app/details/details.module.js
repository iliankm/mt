import {NgModule} from '@angular/core';
import {FormsModule}   from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

import {DetailsComponent} from 'app/details/details.component';

export class DetailsModule {}

DetailsModule.annotations = [
                             new NgModule({

                                 imports: [FormsModule, BrowserModule],

                                 declarations: [DetailsComponent],

                                 exports: [DetailsComponent],

                                 providers: []
                             })
                             ];