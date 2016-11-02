package org.mt.business.boundary.service.employee;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.mt.business.api.boundary.service.employee.EmployeeService;
import org.mt.business.api.boundary.service.employee.argument.CreateUpdateEmployeeArgument;
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

	// create AddressEntityBean
	final AddressEntityBean address = AddressEntityBean.Builder.get()
		.country(createUpdateEmployeeArgument.getAddress().getCountry())
		.city(createUpdateEmployeeArgument.getAddress().getCity())
		.street(createUpdateEmployeeArgument.getAddress().getStreet())
		.zip(createUpdateEmployeeArgument.getAddress().getZip()).build();

	// create list of PhoneEntityBean-s
	final List<PhoneEntityBean> phones = createUpdateEmployeeArgument.getPhones().stream()
		.map(p -> new PhoneEntityBean(p.getType(), p.getPhone())).collect(Collectors.toList());

	// create EmployeeEntityBean
	final EmployeeEntityBean employee = EmployeeEntityBean.Builder.get()
		.name(createUpdateEmployeeArgument.getName()).email(createUpdateEmployeeArgument.getEmail())
		.gender(createUpdateEmployeeArgument.getGender()).address(address).phones(phones).build();

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

}
