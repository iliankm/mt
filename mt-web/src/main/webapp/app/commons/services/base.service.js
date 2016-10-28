import {Observable} from 'rxjs/Observable';

export class BaseService {

    handleError(response) {

	console.error(response);

	if (!$.isEmptyObject(response.text()) && response.json().error) {
	    return Observable.throw(response.json().error);
	} else {
	    return Observable.throw('Server error');
	}
    }
}