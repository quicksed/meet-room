package com.nordclan.test_project.dto.mapper;

import com.nordclan.test_project.dto.employee.EmployeeDto;
import com.nordclan.test_project.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeDto toDto(Employee model) {
        EmployeeDto employee = new EmployeeDto();
        employee.setUsername(model.getUsername());

        return employee;
    }

    public List<EmployeeDto> toDto(Set<Employee> model) {
        return model.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
