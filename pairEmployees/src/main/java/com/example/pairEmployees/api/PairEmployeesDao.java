package com.example.pairEmployees.api;

import com.example.pairEmployees.beans.Employee;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PairEmployeesDao {
    public List<Employee> getPairedEmpl(File file) {


        List<Employee> employees = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(
                    new FileReader(file));
            String line;

            while ((line = bf.readLine()) != null) {
                Employee employee = new Employee();
                String[] tempText = line.split(",");
                employee.setEmployeeId(Integer.parseInt(tempText[0]));
                employee.setProjectId(Integer.parseInt(tempText[1]));
                employee.setDateFrom(LocalDate.parse(tempText[2], Objects.requireNonNull(findMatchingPattern(tempText[2]))));
                LocalDate time = tempText[3] == null ? LocalDate.now() : LocalDate.parse(tempText[3], Objects.requireNonNull(findMatchingPattern(tempText[3])));
                employee.setDateTo(time);

                employees.add(employee);
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
        return employees;

    }

    private DateTimeFormatter findMatchingPattern(String date){
        DateTimeFormatter[] formatters ={
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("MMM dd, yyyy"),
        };
        for (DateTimeFormatter formatter : formatters) {
            try {
                formatter.parse(date);
                return formatter;
            }catch (IllegalArgumentException e){
                System.out.println("Error" );
            }
        }
        return null;
    }
}
