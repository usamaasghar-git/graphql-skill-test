package com.graphql.graphql_test.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long Id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String employeeClass;

    @Column
    private String subjects;

    @Column
    private String attendance;

}
