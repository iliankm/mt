package org.mt.business.api.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Sort criteria object used to pass sort properties for the result sets from
 * frontend to DAOs. <br>
 * Multiple sort criteria are supported. <br>
 * <br>
 *
 * Examples of use: <br>
 * someService.findObjectsByCriteria(..., SortCriteria.get().criteria("id",
 * asc)); <br>
 *
 * someService.findObjectsByCriteria(..., SortCriteria.get().criteria("name",
 * asc).criteria("date", desc)); <br>
 *
 * someService.findObjectsByCriteria(...,
 * SortCriteria.get().criteria("emp.name", asc).criteria("emp.gender", desc));
 *
 *
 */
public class SortCriteria implements Serializable {

    private static final long serialVersionUID = -4307331103757689421L;

    public static enum SortDirection {
	asc, desc
    };

    private Map<String, SortDirection> criteria = new LinkedHashMap<>();

    public static SortCriteria get() {
	return new SortCriteria();
    }

    /**
     * Add single sort criterion.
     *
     * @param property
     *            - sort property (e.g. "id", "emp.name"...)
     * @param sortDirection
     * @return this object with the added sort criteria
     */
    public SortCriteria criterion(String property, SortDirection sortDirection) {
	criteria.put(property, sortDirection);

	return this;
    }

    /**
     *
     * @return sort criteria in the insertion order
     */
    public Map<String, SortDirection> getCriteria() {
	return Collections.unmodifiableMap(criteria);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((criteria == null) ? 0 : criteria.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SortCriteria other = (SortCriteria) obj;
	if (criteria == null) {
	    if (other.criteria != null)
		return false;
	} else if (!criteria.equals(other.criteria))
	    return false;
	return true;
    }



}
