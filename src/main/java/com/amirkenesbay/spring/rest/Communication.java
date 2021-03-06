package com.amirkenesbay.spring.rest;

import com.amirkenesbay.spring.rest.entity.Department;
import com.amirkenesbay.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private static final String URL = "http://localhost:8080/spring_course_rest/api/employees";
    private static final String URL_DP = "http://localhost:8080/spring_course_rest/api/departments";

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
                });
        List<Employee> allEmployees = responseEntity.getBody();
        return allEmployees;
    }

    public List<Department> getAllDepartments() {
        ResponseEntity<List<Department>> responseEntity =
                restTemplate.exchange(URL_DP, HttpMethod.GET, null, new ParameterizedTypeReference<List<Department>>() {
                });
        List<Department> allDepartments = responseEntity.getBody();
        return allDepartments;
    }

    public Employee getEmployee(int id) {
        Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);
        return employee;
    }

    public Department getDepartment(int id) {
        Department department = restTemplate.getForObject(URL_DP + "/" + id, Department.class);
        return department;
    }

    public void saveOrUpdateEmployee(Employee employee) {
        int id = employee.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);
            System.out.println("New Employee was added to DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);
            System.out.println("Employee with ID " + id + " was updated");
        }
    }

    public void saveOrUpdateDepartment(Department department) {
        int id = department.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_DP, department, String.class);
            System.out.println("New Department was added to DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL_DP, department);
            System.out.println("Department with ID " + id + " was updated");
        }
    }

    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Employee with ID " + id + " was deleted from DB");
    }

    public void deleteDepartment(int id) {
        restTemplate.delete(URL_DP + "/" + id);
        System.out.println("Department with ID " + id + " was deleted from DB");
    }
}
