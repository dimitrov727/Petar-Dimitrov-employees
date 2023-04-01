package com.example.pairEmployees.api;

import com.example.pairEmployees.beans.Employee;
import com.example.pairEmployees.beans.PairedEmpl;

import java.util.List;

public interface IPairEmployeesService {
    List<Employee> getEml();
    List<Employee> getDuplicatedEml();
    List<PairedEmpl> getPairedEmp();
    String getPath();
    long calculateDuration(Employee employee1, Employee employee2);
}
