import {Observable} from 'rxjs/Observable';

export class BaseService {

    handleError(response) {

        console.error(response);

        let json = BaseService.safeParseJSON(response.text());

        if (json && json.error) {
            return Observable.throw(json.error);
        } else {
            return Observable.throw('Server error');
        }
    }

    /**
     * Safe parse passed string to json
     */
    static safeParseJSON(text) {

        let json = null;

        try {
            json = JSON.parse(text);
        }
        catch(e) {
            json = null;
        }

        return json;
    }
}