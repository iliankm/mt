import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {LocationStrategy, HashLocationStrategy} from '@angular/common';

import {routing} from './app.routes';

import {AppComponent} from './app.component';
import {PageNotFoundComponent} from 'app/page-not-found/page-not-found.component';
import {ListComponent} from 'app/list/list.component';
import {DetailsComponent} from 'app/details/details.component';
import {WizardModule} from 'app/wizard/wizard.module';

export class AppModule {

}

AppModule.annotations = [
                        	new NgModule({

                        		imports: [
                        		          BrowserModule,
                        		          FormsModule,
                        		          routing,
                        		          WizardModule
                        		          ],

                        		declarations: [AppComponent,
                        		               PageNotFoundComponent,
                        		               ListComponent,
                        		               DetailsComponent
                        		              ],

                        		providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],

                        		bootstrap: [AppComponent]
                        	})
                        ];
