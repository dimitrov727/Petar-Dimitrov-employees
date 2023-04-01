package com.example.pairEmployees.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PairEmployeesController {
    private final IPairEmployeesService service;

    public PairEmployeesController(PairEmployeesService service) {
        this.service = service;
    }

    @GetMapping("/emp")
    public String get(Model model) {
        model.addAttribute("employee", service.getEml());
        return "employee";

    }

    @GetMapping("/pairedEmp")
    public String getPairedEmpl(Model model) {
        model.addAttribute("pairedEmployees", service.getPairedEmp());
        return "pairedEmployees";

    }

    @GetMapping("/home")
    public String getPath(Model model) {
        model.addAttribute("chooseFile", "This is text");
        service.getPath();
        return "chooseFile";

    }

    @PostMapping("/button")
    public String myButton() {
        return "redirect:/pairedEmp";
    }
}
