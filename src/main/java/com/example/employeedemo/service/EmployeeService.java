package com.example.employeedemo.service;

import com.example.employeedemo.client.RestClient;
import com.example.employeedemo.config.Errors;
import com.example.employeedemo.dto.AddressDTO;
import com.example.employeedemo.model.Employee;
import com.example.employeedemo.repository.EmployeeRepository;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private RestClient restClient;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RestClient restClient){

        this.employeeRepository = employeeRepository;
        this.restClient = restClient;
    }
    /*
    returns a list of employees by their names
    */
    public List<Employee> employeeByName(){
        List<Employee> empList = employeeRepository.findAll();
        return empList.stream().collect(Collectors.toList());
    }

    /**
    saves an employee record in the database
     **/
    public Employee saveEmployee(Employee employee){

        return employeeRepository.save(employee);
    }

    /*
    updates an existing employee record in the database
     */
    public Employee updateEmployee(Employee employee,Long id){
        Employee emp=employeeNotFound(id);
        return employeeRepository.save(employee);
    }

    /**
     * deletes an employee record from the database
     * @param id
     */
    public void deleteEmployee(Long id){
        //throw new NullPointerException("unexpected exception");
        Employee employee= employeeNotFound(id);
        employeeRepository.delete(employee);
    }

    private Employee employeeNotFound(Long id){
        return employeeRepository.findById(id).orElseThrow(Errors.NOT_FOUND::asException);
        //.orElse(null);
    }

}
