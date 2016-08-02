import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

const LIST_FILTERS = ['all', 'archived'];

export class ListComponent {

	constructor(router, route) {

		this.router = router;

		this.route = route;
	}

	static get parameters() {

		return [[Router], [ActivatedRoute]]
	}

	ngOnInit() {

		let me = this;

	    this.sub = this.route.params.subscribe(params => {

	    	let filter = params['filter'];

	    	if (LIST_FILTERS.indexOf(filter) == -1) {
	    		me.router.navigate(['/list', LIST_FILTERS[0]]);
	    	}
	    	else {
	    		me.filter = filter;
	    	}
	    });
	}

	ngOnDestroy() {

		  this.sub.unsubscribe();
	}

}

ListComponent.annotations = [
                            	new Component({
                            		selector: 'emp-list',
                            		templateUrl: 'app/list/list.template.html',
                            		styleUrls:  []
                            	})
];
