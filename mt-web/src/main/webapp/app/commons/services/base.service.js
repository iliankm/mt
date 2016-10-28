import {Observable} from 'rxjs/Observable';

export class BaseService {

	handleError(response) {

	    console.error(response);

	    return Observable.throw((!$.isEmptyObject(response.text()) && response.json().error) || 'Server error');
	}
}