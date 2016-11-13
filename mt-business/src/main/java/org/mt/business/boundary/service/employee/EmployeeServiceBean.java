package org.mt.business.boundary.service.employee;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.mt.business.api.boundary.service.employee.EmployeeService;
import org.mt.business.api.boundary.service.employee.argument.AddressArgument;
import org.mt.business.api.boundary.service.employee.argument.CreateUpdateEmployeeArgument;
import org.mt.business.api.boundary.service.employee.argument.PhoneArgument;
import org.mt.business.api.domain.employee.Employee;
import org.mt.business.api.exception.ResourceNotFoundException;
import org.mt.business.control.repository.employee.EmployeeRepository;
import org.mt.business.domain.employee.AddressEntityBean;
import org.mt.business.domain.employee.EmployeeEntityBean;
import org.mt.business.domain.employee.PhoneEntityBean;

public class EmployeeServiceBean implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findById(String id) {

	Objects.requireNonNull(id);

	final Employee employee = employeeRepository.findById(id);

	if (employee == null) {
	    throw new ResourceNotFoundException();
	}

	return employee;
    }

    @Override
    public Employee create(@Valid CreateUpdateEmployeeArgument createUpdateEmployeeArgument) {

	Objects.requireNonNull(createUpdateEmployeeArgument);

	// create EmployeeEntityBean
	final EmployeeEntityBean employee = EmployeeEntityBean.Builder.get()
		.identificationNumber(createUpdateEmployeeArgument.getIdentificationNumber())
		.name(createUpdateEmployeeArgument.getName()).email(createUpdateEmployeeArgument.getEmail())
		.gender(createUpdateEmployeeArgument.getGender()).build();

	// persist employee
	employeeRepository.save(employee);

	return employee;
    }

    @Override
    public void update(String employeeId, @Valid CreateUpdateEmployeeArgument createUpdateEmployeeArgument) {

	Objects.requireNonNull(employeeId);

	Objects.requireNonNull(createUpdateEmployeeArgument);

	final int updatedCount = employeeRepository.update(employeeId, createUpdateEmployeeArgument);

	if (updatedCount == 0) {
	    throw new ResourceNotFoundException();
	}
    }

    @Override
    public void update(String employeeId, @Valid AddressArgument addressArgument) {

	Objects.requireNonNull(employeeId);

	Objects.requireNonNull(addressArgument);

	final AddressEntityBean address = AddressEntityBean.Builder.get().country(addressArgument.getCountry())
		.city(addressArgument.getCity()).street(addressArgument.getStreet()).zip(addressArgument.getZip())
		.build();

	final int updatedCount = employeeRepository.update(employeeId, address);

	if (updatedCount == 0) {
	    throw new ResourceNotFoundException();
	}
    }

    @Override
    public void update(String employeeId, @Valid Set<PhoneArgument> phonesArgument) {

	Objects.requireNonNull(employeeId);

	final Set<PhoneEntityBean> phones = phonesArgument == null ? Collections.emptySet()
		: phonesArgument.stream().map(pa -> new PhoneEntityBean(pa.getType(), pa.getPhone()))
			.collect(Collectors.toSet());

	final int updatedCount = employeeRepository.update(employeeId, phones);

	if (updatedCount == 0) {
	    throw new ResourceNotFoundException();
	}
    }

}
