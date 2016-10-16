import {Observable} from 'rxjs/Observable';

export class BaseService {

	handleError(response) {

		console.error(response);

		return Observable.throw(response.json().error || 'Server error');
	}
}