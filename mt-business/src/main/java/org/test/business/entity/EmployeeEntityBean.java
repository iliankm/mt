package org.test.business.entity;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.test.business.api.entity.AddressEntity;
import org.test.business.api.entity.EmployeeEntity;
import org.test.business.api.entity.PhoneEntity;
import org.test.business.api.entity.util.Gender;

/**
 * Immutable EmployeeEntity
 *
 */
@Entity(value = "employee", noClassnameStored = false)
public class EmployeeEntityBean extends BaseEntityBean implements EmployeeEntity
{
    private static final long serialVersionUID = -2454104054295746441L;

    @NotNull(message = "validation_employee_name_is_mandatory")
    @Indexed
    private String name;

    @NotNull(message = "validation_employee_gender_is_mandatory")
    private Gender gender;

    @NotNull(message = "validation_employee_address_is_mandatory")
    @Valid
    @Embedded
    private AddressEntityBean address;

    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "validation_employee_email_invalid")
    @Indexed
    private String email;

    @Embedded
    @Valid
    private List<PhoneEntityBean> phones;

    @Min(value = 0, message = "validation_employee_salary_invalid")
    private long salary;

    private Set<ObjectId> substituteIds;

    /*
     * Default no-args constructor needed by persistence framework
     */
    private EmployeeEntityBean() {}

    private EmployeeEntityBean(Builder builder) {

        this.name = builder.name;
        this.gender = builder.gender;
        this.address = builder.address;
        this.email = builder.email;
        this.phones = Collections.unmodifiableList(builder.phones);
        this.salary = builder.salary;
        this.substituteIds = builder.substituteIds != null
                ? builder.substituteIds.stream().map(sid -> new ObjectId(sid)).collect(Collectors.toSet()) : null;
    }

    /**
     * Builder for EmployeeEntity
     */
    public static class Builder {

        private String name;
        private Gender gender;
        private AddressEntityBean address;
        private String email;
        private List<PhoneEntityBean> phones;
        private long salary;
        private Set<String> substituteIds;

        public Builder name(String name) {

            this.name = name;
            return this;
        }

        public Builder gender(Gender gender) {

            this.gender = gender;
            return this;
        }

        public Builder address(AddressEntityBean address) {

            this.address = address;
            return this;
        }

        public Builder email(String email) {

            this.email = email;
            return this;
        }

        public Builder phones(List<PhoneEntityBean> phones) {

            this.phones = phones;
            return this;
        }

        public Builder salary(long salary) {

            this.salary = salary;
            return this;
        }

        public Builder substituteIds(Set<String> substituteIds) {

            this.substituteIds = substituteIds;
            return this;
        }

        public EmployeeEntityBean build() {

            return new EmployeeEntityBean(this);
        }
    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public Gender getGender() {

        return gender;
    }

    @Override
    public AddressEntity getAddress() {

        return address;
    }

    @Override
    public String getEmail() {

        return email;
    }

    @Override
    public List<? extends PhoneEntity> getPhones() {

        return phones != null ? Collections.unmodifiableList(phones) : Collections.emptyList();
    }

    @Override
    public long getSalary() {

        return salary;
    }

    @Override
    public Set<String> getSubstituteIds() {

        return substituteIds != null ? substituteIds.stream().map(oid -> oid.toHexString()).collect(Collectors.toSet())
                : Collections.emptySet();
    }
}
