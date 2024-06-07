package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.repository.DepartmentRepository;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee =  modelMapper.map(employeeDto, Employee.class);
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(()-> new ResourceNotFoundException("Department not exists with id: "+employeeDto.getDepartmentId()));

        employee.setDepartment(department);
        Employee saveEmployee = employeeRepository.save(employee);
        EmployeeDto saveEmployeeDto = modelMapper.map(saveEmployee,EmployeeDto.class);
        return saveEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Employee not exists for given id :"+empId));
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map((employee -> modelMapper.map(employee,EmployeeDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {

        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exists with given id:"+id));
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(()-> new ResourceNotFoundException("Department not exists with id: "+employeeDto.getDepartmentId()));

        employee.setDepartment(department);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        EmployeeDto updatedEmployee = modelMapper.map(employee,EmployeeDto.class);
        employeeRepository.save(employee);
        return updatedEmployee;
    }

    @Override
    public String deleteById(Long empId) {
        employeeRepository.deleteById(empId);
        return "Employee Deleted Successfully !!!";
    }
}
