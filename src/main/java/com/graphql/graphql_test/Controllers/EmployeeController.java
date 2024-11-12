package com.graphql.graphql_test.Controllers;

import com.graphql.graphql_test.Entities.Employee;
import com.graphql.graphql_test.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryMapping
    public List<Employee> listEmployees(@Argument int page, @Argument int size, @Argument String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
        return employeeRepository.findAll(pageRequest).getContent();
    }

    @QueryMapping
    public Employee getEmployee(@Argument Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Employee addEmployee(@Argument String name, @Argument Integer age,
                                @Argument String employeeClass, @Argument String subjects,
                                @Argument String attendance) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employee.setEmployeeClass(employeeClass);
        employee.setSubjects(subjects);
        employee.setAttendance(attendance);
        return employeeRepository.save(employee);
    }

    @MutationMapping
    public Employee updateEmployee(@Argument Long id, @Argument String name, @Argument Integer age,
                                             @Argument String employeeClass, @Argument String subjects,
                                             @Argument String attendance) {
        Optional<Employee> optionalEmployee = Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found")));
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (name != null) employee.setName(name);
            if (age != null) employee.setAge(age);
            if (employeeClass != null) employee.setEmployeeClass(employeeClass);
            if (subjects != null) employee.setSubjects(subjects);
            if (attendance != null) employee.setAttendance(attendance);
            return employeeRepository.save(employee);
        }
        return null;
    }
}
