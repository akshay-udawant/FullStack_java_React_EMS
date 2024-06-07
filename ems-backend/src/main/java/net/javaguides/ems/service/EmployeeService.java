package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long empId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    String deleteById(Long empId);
}
