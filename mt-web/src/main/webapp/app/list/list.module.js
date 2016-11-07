import {NgModule} from '@angular/core';
import {FormsModule}   from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

import {ListComponent} from 'app/list/list.component';

export class ListModule {}

ListModule.annotations = [
                             new NgModule({

                                 imports: [FormsModule, BrowserModule],

                                 declarations: [ListComponent],

                                 exports: [ListComponent],

                                 providers: []
                             })
                             ];