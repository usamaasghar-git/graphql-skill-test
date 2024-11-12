package com.graphql.graphql_test.Repositories;

import com.graphql.graphql_test.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
