import { provideRouter} from '@angular/router';

import { ListComponent } from 'app/list/list.component';
import { DetailsComponent } from 'app/details/details.component';
import { WizardComponent } from 'app/wizard/wizard.component';

const routes = [
	{ path: '', redirectTo: 'list/all', pathMatch: 'full'},
	{ path: 'list/:filter', component: ListComponent },
	{ path: 'details', component: DetailsComponent },
	{ path: 'wizard', component: WizardComponent }
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(routes)
];