import {NgModule} from '@angular/core';
import {FormsModule}   from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

import {PageNotFoundComponent} from 'app/page-not-found/page-not-found.component';

export class PageNotFoundModule {}

PageNotFoundModule.annotations = [
                             new NgModule({

                            	 imports: [FormsModule, BrowserModule],

                            	 declarations: [PageNotFoundComponent],

                            	 exports: [PageNotFoundComponent],

                            	 providers: []
                             })
                             ];