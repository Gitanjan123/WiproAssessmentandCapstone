package com.wipro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @GetMapping("/")
    public String showForm() {
        return "First";
    }

    @PostMapping("/addStudent")
    public ModelAndView submitForm(Student student) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("student", student);
        mv.setViewName("Success");
        return mv;
    }
}