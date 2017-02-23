package org.mt.business.api.domain.employee;

/**
 * Employee search criteria immutable object.
 */
public class EmployeeSearchCriteria {

    private final String name;

    private final Gender gender;

    private final String email;

    private EmployeeSearchCriteria(Builder builder) {
        this.name = builder.name;
        this.gender = builder.gender;
        this.email = builder.email;
    }

    public static class Builder {

        private String name;
        private Gender gender;
        private String email;

        public Builder get() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public EmployeeSearchCriteria build() {
            return new EmployeeSearchCriteria(this);
        }
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        EmployeeSearchCriteria other = (EmployeeSearchCriteria) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (gender != other.gender)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }


}
