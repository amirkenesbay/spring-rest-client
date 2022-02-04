package com.amirkenesbay.spring.rest;

import com.amirkenesbay.spring.rest.configuration.MyConfig;
import com.amirkenesbay.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        List<Employee> allEmployees = communication.getAllEmployees();
        System.out.println(allEmployees);
//        Employee empByID = communication.getEmployee(3);
//        System.out.println(empByID);
//        Employee employee = new Employee("Smitty", "Flowers", "HR", 500);
//        employee.setId(23);
//        communication.saveOrUpdateEmployee(employee);
//        communication.deleteEmployee(23);
    }
}
