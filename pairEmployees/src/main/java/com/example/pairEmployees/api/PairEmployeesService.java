package com.example.pairEmployees.api;

import com.example.pairEmployees.beans.Employee;
import com.example.pairEmployees.beans.FileChooser;
import com.example.pairEmployees.beans.PairedEmpl;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PairEmployeesService implements IPairEmployeesService {

    @Override
    public List<Employee> getEml() {
        PairEmployeesDao pairEmployeesDao = new PairEmployeesDao();
        File fil = new File("C:\\Users\\dimit\\OneDrive\\Работен плот\\sheet1.csv");
        return pairEmployeesDao.getPairedEmpl(fil);
    }

    @Override
    public List<Employee> getDuplicatedEml() {

        return getEml().stream()
                .collect(Collectors.groupingBy(Employee::getProjectId))
                .values()
                .stream()
                .filter(list -> list.size() > 1)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<PairedEmpl> getPairedEmp() {
        List<PairedEmpl> pairedEmpls = new ArrayList<>();

        for (int i = 0; i < getDuplicatedEml().size() - 1; i++) {
            Employee employee1 = getDuplicatedEml().get(i);
            for (int j = 1; j < getDuplicatedEml().size(); j++) {
                Employee employee2 = getDuplicatedEml().get(j);
                if (employee1.getProjectId() == employee2.getProjectId()) {
                    int duration = (int) calculateDuration(employee1, employee2);
                    PairedEmpl pairedEmpl = new PairedEmpl(
                            employee1.getEmployeeId(), employee2.getEmployeeId(), employee2.getProjectId(), duration);
                    pairedEmpls.add(pairedEmpl);
                }
            }

        }
        pairedEmpls = pairedEmpls.stream().distinct().collect(Collectors.toList());
        pairedEmpls.forEach(System.out::println);
        System.out.println(getDuplicatedEml());

        return pairedEmpls;

    }

    @Override
    public String getPath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setVisible(true);
        return "e";
    }

    @Override
    public long calculateDuration(Employee employee1, Employee employee2) {
        LocalDate start = employee1.getDateFrom().isAfter(employee2.getDateFrom()) ? employee1.getDateFrom() : employee2.getDateFrom();
        LocalDate end = employee1.getDateTo().isAfter(employee2.getDateTo()) ? employee2.getDateTo() : employee1.getDateTo();

        return ChronoUnit.DAYS.between(start, end);
    }


}
