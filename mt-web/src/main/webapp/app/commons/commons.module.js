import {NgModule, APP_INITIALIZER} from '@angular/core';
import {FormsModule}   from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';

import {CountrySelectComponent} from 'app/commons/components/country-select/country-select.component';

import {ValidateEmailDirective} from 'app/commons/directives/validate-email/validate-email.directive';
import {ValidatePhoneDirective} from 'app/commons/directives/validate-phone/validate-phone.directive';

import {MessagesService} from 'app/commons/services/messages/messages.service.js';
import {EmployeesService} from 'app/commons/services/employees/employees.service.js';

export class CommonsModule {

}

CommonsModule.annotations = [
                            new NgModule({

                                imports: [FormsModule, BrowserModule, HttpModule],

                                declarations: [
                                                CountrySelectComponent,
                                                ValidateEmailDirective,
                                                ValidatePhoneDirective
                                              ],

                                exports: [
                                            CountrySelectComponent,
                                            ValidateEmailDirective,
                                            ValidatePhoneDirective
                                          ],

                                providers: [
                                            MessagesService,
                                            {
                                                provide: APP_INITIALIZER,
                                                useFactory: (messagesService) => () => messagesService.load(),
                                                deps: [MessagesService],
                                                multi: true
                                            },
                                            EmployeesService
                                            ]

                            })
                        ];
