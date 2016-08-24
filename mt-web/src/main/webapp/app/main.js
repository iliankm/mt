import {bootstrap} from '@angular/platform-browser-dynamic';
import {APP_ROUTER_PROVIDERS} from './app.routes';
import {AppComponent} from './app.component';
import {
	  Location,
	  LocationStrategy,
	  HashLocationStrategy
	} from '@angular/common';
import {enableProdMode} from '@angular/core';

enableProdMode();

bootstrap(AppComponent, [
	APP_ROUTER_PROVIDERS,
	{provide: LocationStrategy, useClass: HashLocationStrategy}
]);