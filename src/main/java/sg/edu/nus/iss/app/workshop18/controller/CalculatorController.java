package sg.edu.nus.iss.app.workshop18.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.app.workshop18.model.Models;
import sg.edu.nus.iss.app.workshop18.service.CompatibilityService;

@Controller
@RequestMapping(path="/percentage")
public class CalculatorController {
    
    @Autowired
    private CompatibilityService compatibilitySvc;

    @GetMapping
    public String getPercentage(@RequestParam(required=true) String firstName,
        @RequestParam(required=true) String secondName,
        Model model) throws IOException {

        Optional<Models> p = compatibilitySvc.getModels(firstName, secondName, model);
        System.out.println("======= p: "+p);
        System.out.println("======= p.getFirstName"+p.get().getFirstName());
        model.addAttribute("percentage", p.get());
        return "percentage";
    }
}