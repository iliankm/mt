package org.mt.business.api.domain.employee;

import java.util.List;
import java.util.Set;

import org.mt.business.api.domain.IdentifiableObject;

public interface Employee extends IdentifiableObject {

    String getIdentificationNumber();

    String getName();

    Gender getGender();

    Address getAddress();

    String getEmail();

    List<? extends Phone> getPhones();

    Set<String> getSubstituteIds();
}
