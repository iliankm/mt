package org.mt.business.boundary.service.employee;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.mt.business.api.boundary.service.employee.EmployeeService;
import org.mt.business.api.boundary.service.employee.argument.CreateEmployeeArgument;
import org.mt.business.api.domain.employee.Employee;
import org.mt.business.control.repository.employee.EmployeeRepository;
import org.mt.business.domain.employee.AddressEntityBean;
import org.mt.business.domain.employee.EmployeeEntityBean;
import org.mt.business.domain.employee.PhoneEntityBean;

public class EmployeeServiceBean implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findById(Object id) {

	Objects.requireNonNull(id);

	return employeeRepository.findById(id);
    }

    @Override
    public Employee create(CreateEmployeeArgument createEmployeeArgument) {

	Objects.requireNonNull(createEmployeeArgument);

	//create AddressEntityBean
	final AddressEntityBean address = AddressEntityBean.Builder.get()
		.country(createEmployeeArgument.getAddress().getCountry())
		.city(createEmployeeArgument.getAddress().getCity())
		.street(createEmployeeArgument.getAddress().getStreet())
		.zip(createEmployeeArgument.getAddress().getZip()).build();

	//create list of PhoneEntityBean-s
	final List<PhoneEntityBean> phones = createEmployeeArgument.getPhones().stream()
		.map(p -> new PhoneEntityBean(p.getType(), p.getPhone())).collect(Collectors.toList());

	//create EmployeeEntityBean
	final EmployeeEntityBean employee = EmployeeEntityBean.Builder.get().name(createEmployeeArgument.getName())
		.email(createEmployeeArgument.getEmail()).gender(createEmployeeArgument.getGender()).address(address)
		.phones(phones).build();

	//persist employee
	employeeRepository.save(employee);

	return employee;
    }

}
