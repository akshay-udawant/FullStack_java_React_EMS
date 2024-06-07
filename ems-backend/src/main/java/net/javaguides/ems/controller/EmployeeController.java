package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto saveEmployeeDto =  employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(saveEmployeeDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long empId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDtos,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empId,@RequestBody EmployeeDto employeeDto){
        EmployeeDto updatedEmployee = employeeService.updateEmployee(empId,employeeDto);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empId){
        employeeService.deleteById(empId);
        return new ResponseEntity<String>("Employee Deleted with id"+empId,HttpStatus.OK);
    }
}
