package com.wipro.WiproSpringboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  // Fixed: was @RestController
public class HomeController {

    @GetMapping("/home")
    public ModelAndView showHomePage(Model model) {
        model.addAttribute("message", "Welcome to Java4s Spring Boot Tutorials");
        return new ModelAndView("home");
    }
}