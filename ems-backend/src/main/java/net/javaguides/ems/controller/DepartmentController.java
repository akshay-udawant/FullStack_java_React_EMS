package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
@CrossOrigin("*")
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartmnet(@RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(departmentDto1, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDEpartmentById(@PathVariable("id") Long departmentId){
        DepartmentDto departmentDto = departmentService.getDepartmentByID(departmentId);
        return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentDtos,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDto departmentDto){
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentId,departmentDto);
        return new ResponseEntity<>(updatedDepartment,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<String>("Department deleted successfully", HttpStatus.OK);
    }
}
