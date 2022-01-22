package com.example.employeedemo.controller;

import com.example.employeedemo.client.RestClient;
import com.example.employeedemo.dto.AddressDTO;
import com.example.employeedemo.model.Employee;
import com.example.employeedemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RestClient restClient;


    @GetMapping(value = "/employee")
    public List<Employee> getEmployeesByName(){
        return employeeService.employeeByName();
    }

    @PutMapping(value="/employee")
    public void insertEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
    }

    @PostMapping(value="/employee/{id}")
    public void updateEmployee(@PathVariable("id") Long id,@RequestParam("name") String queryParam,@RequestBody Employee employee){
        employeeService.updateEmployee(employee,id);
    }

    @DeleteMapping(value = "/employee/{id}")
    public void deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping(value="employee/address/{id}")
    public ResponseEntity<AddressDTO> getEmployeeAddress(@PathVariable("id") Long id){
     return restClient.getEmployeeAddress(id);
    }
}
