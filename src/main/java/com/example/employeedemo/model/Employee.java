package com.example.employeedemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
@Data
public class Employee {
    @Id
    private Long id;
    private String name;
    private String designation;
    private double salary;
}