package org.mt.business.api.entity;

import java.util.List;
import java.util.Set;

import org.mt.business.api.entity.util.Gender;

public interface EmployeeEntity extends BaseEntity {

    public String getName();

    public Gender getGender();

    public AddressEntity getAddress();

    public String getEmail();

    public List<? extends PhoneEntity> getPhones();

    public long getSalary();

    public Set<String> getSubstituteIds();

}
