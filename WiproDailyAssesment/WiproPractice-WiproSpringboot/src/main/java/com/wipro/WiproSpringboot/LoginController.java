package com.wipro.WiproSpringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    JdbcTemplate jdbcobj;

    // GET: Just show the login page
    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    // POST: Handle form submission
    @PostMapping("/login")
    public String checkUser(@RequestParam String name,
                            @RequestParam String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE name=? AND password=?";
        int count = jdbcobj.queryForObject(sql, Integer.class, name, password);
        if (count > 0) {
            return "redirect:/home";
        } else {
            return "redirect:/login?error=true";
        }
    }
}