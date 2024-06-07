package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.repository.DepartmentRepository;
import net.javaguides.ems.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department saveDepartment = departmentRepository.save(department);
        return modelMapper.map(saveDepartment,DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByID(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()-> new ResourceNotFoundException("Department is not exists for id :"+departmentId));
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDtos= departments.stream().map((department)-> modelMapper.map(department,DepartmentDto.class)).collect(Collectors.toList());
        return departmentDtos;
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department not exists with id :"+departmentId));
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        Department updatedDepartment = departmentRepository.save(department);
        return modelMapper.map(updatedDepartment,DepartmentDto.class);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
