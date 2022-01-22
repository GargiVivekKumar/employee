package com.example.employeedemo.service;

import com.example.employeedemo.client.RestClient;
import com.example.employeedemo.config.CustomException;
import com.example.employeedemo.config.Errors;
import com.example.employeedemo.model.Employee;
import com.example.employeedemo.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    private Employee employee;
    private Employee employee1;
    List<Employee> employeeList;
    @Mock
    private RestClient restClient;

    @Before
    public void beforeSetUp(){
        employeeService = new EmployeeService(employeeRepository,restClient);
        employee = new Employee();
        employeeList = new ArrayList<>();
        employee.setId(1L);
        employee.setDesignation("Developer");
        employee.setName("Gargi");
        employee.setSalary(50000);
        employeeList.add(employee);
        employee1 = new Employee();
        employee1.setId(2L);
        employee1.setDesignation("Manager");
        employee1.setName("Mohit");
        employee1.setSalary(50000);
        employeeList.add(employee1);
    }

    @Test
    public void testEmployeeByName(){
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> empList = employeeService.employeeByName();
        Assert.assertFalse(empList.isEmpty());
        Assert.assertEquals(empList.get(0).getName(),employee.getName());
    }

    @Test
    public void testSaveEmployee(){
       // Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.saveEmployee(employee);
        Mockito.verify(employeeRepository,Mockito.times(1)).save(employee);
    }

    @Test
    public void testUpdateEmployee(){
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        employee.setName("Ruhi");
        Employee result =employeeService.updateEmployee(employee, employee.getId());
        Assert.assertEquals(employee.getName(),result.getName());
    }

    @Test
    public void testDeleteEmployee(){
        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
       employeeService.deleteEmployee(employee.getId());
       Mockito.verify(employeeRepository,Mockito.times(1)).delete(employee);
    }

    @Test
    public void testNegativeForDeleteEmployee(){
        try {
            employeeService.deleteEmployee(employee.getId());
           Assert.fail("Expecting custom exception");
        }catch (CustomException e){Mockito.verify(employeeRepository,Mockito.times(0)).delete(employee);
         Assert.assertEquals(e.getMessage(), Errors.NOT_FOUND.getDefaultMessage());
        }catch (Exception e){
            Assert.fail("unexpected exception"+e);
        }
    }
}
