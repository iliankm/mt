package org.mt.business.control.dao.util;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.mt.business.api.entity.util.SortCriteria;
import org.mt.business.api.entity.util.SortCriteria.SortDirection;

public class OrderGenerator {

    public String generate(SortCriteria sortCriteria) {

	StringBuilder stringBuilder = new StringBuilder();

	Map<String, SortDirection> criteria = sortCriteria.getCriteria();

	Set<Entry<String, SortDirection>> entrySet = criteria.entrySet();

	for (Entry<String, SortDirection> entry : entrySet) {
	    String property = entry.getKey();
	    SortDirection sortDirection = entry.getValue();

	    switch (sortDirection) {
	    case asc: {
		if (stringBuilder.length() > 0) {
		    stringBuilder.append(",");
		}

		stringBuilder.append(property);
	    }
		break;

	    case desc: {
		if (stringBuilder.length() > 0) {
		    stringBuilder.append(",");
		}

		stringBuilder.append("-");
		stringBuilder.append(property);
	    }
		break;

	    default:
		break;
	    }

	}

	return stringBuilder.toString();
    }

}
