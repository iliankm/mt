import {NgModule, APP_INITIALIZER} from '@angular/core';
import {FormsModule}   from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';

import {CountrySelectComponent} from 'app/commons/components/country-select/country-select.component';
import {ModalComponent} from 'app/commons/components/modal/modal.component';
import {UploadComponent} from 'app/commons/components/upload/upload.component';

import {ValidateEmailDirective} from 'app/commons/directives/validate-email/validate-email.directive';
import {ValidatePhoneDirective} from 'app/commons/directives/validate-phone/validate-phone.directive';

import {AlertsService} from 'app/commons/services/alerts/alerts.service';
import {MessagesService} from 'app/commons/services/messages/messages.service';
import {EmployeesService} from 'app/commons/services/employees/employees.service';

export class CommonsModule {

}

CommonsModule.annotations = [
                            new NgModule({

                                imports: [FormsModule, BrowserModule, HttpModule],

                                declarations: [
                                                CountrySelectComponent,
                                                ModalComponent,
                                                UploadComponent,
                                                ValidateEmailDirective,
                                                ValidatePhoneDirective
                                              ],

                                exports: [
                                            CountrySelectComponent,
                                            ModalComponent,
                                            UploadComponent,
                                            ValidateEmailDirective,
                                            ValidatePhoneDirective
                                          ],

                                providers: [
                                            AlertsService,
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
