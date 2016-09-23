import {NgModule} from '@angular/core';
import {FormsModule}   from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';

import {CountrySelectComponent} from 'app/commons/components/country-select/country-select.component';
import {ValidateEmailDirective} from 'app/commons/directives/validate-email/validate-email.directive';
import {ValidatePhoneDirective} from 'app/commons/directives/validate-phone/validate-phone.directive';

export class CommonsModule {

}

CommonsModule.annotations = [
                        	new NgModule({

                        		imports: [FormsModule, BrowserModule],

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

                        		providers: []

                        	})
                        ];
