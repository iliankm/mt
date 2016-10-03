package org.mt.business.api.domain.employee;

import java.util.List;
import java.util.Set;

import org.mt.business.api.domain.IdentifiableObject;

public interface Employee extends IdentifiableObject {

    public String getName();

    public Gender getGender();

    public Address getAddress();

    public String getEmail();

    public List<? extends Phone> getPhones();

    public long getSalary();

    public Set<String> getSubstituteIds();

}
