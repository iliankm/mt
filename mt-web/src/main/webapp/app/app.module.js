import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {LocationStrategy, HashLocationStrategy} from '@angular/common';

import {routing} from './app.routes';

import {AppComponent} from './app.component';
import {CommonsModule} from 'app/commons/commons.module';
import {PageNotFoundModule} from 'app/page-not-found/page-not-found.module';
import {ListModule} from 'app/list/list.module';
import {DetailsModule} from 'app/details/details.module';
import {WizardModule} from 'app/wizard/wizard.module';

export class AppModule {

}

AppModule.annotations = [
                            new NgModule({

                                imports: [
                                          BrowserModule,
                                          FormsModule,
                                          routing,

                                          CommonsModule,
                                          PageNotFoundModule,
                                          WizardModule,
                                          DetailsModule,
                                          ListModule
                                          ],

                                declarations: [AppComponent],

                                providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],

                                bootstrap: [AppComponent]
                            })
                        ];
