package org.test.business.api.entity;

import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.test.business.api.entity.util.Gender;

public interface EmployeeEntity extends BaseEntity {

    public String getName();

    public void setName(String name);

    public Gender getGender();

    public void setGender(Gender gender);

    public AddressEntity getAddress();

    public String getEmail();

    public void setEmail(String email);

    public List<? extends PhoneEntity> getPhones();

    public long getSalary();

    public void setSalary(long salary);

    public Set<ObjectId> getSubstituteIds();

}
