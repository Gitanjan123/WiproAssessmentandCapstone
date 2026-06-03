package com.wipro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    // LOGIN PAGE
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {

        return "login";
    }

    // LOGIN FORM SUBMIT
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(
            @RequestParam String username,
            @RequestParam String password) {

        ModelAndView mv = new ModelAndView();

        if (username.equals("admin") &&
                password.equals("1234")) {

            mv.setViewName("redirect:/profile");

        } else {

            mv.setViewName("login");
            mv.addObject("error",
                    "Invalid Credentials!");
        }

        return mv;
    }

    // SIGNUP PAGE
    @RequestMapping(value = "/signup",
            method = RequestMethod.GET)
    public String showSignup() {

        return "signup";
    }

    // SIGNUP FORM SUBMIT
    @RequestMapping(value = "/signup",
            method = RequestMethod.POST)
    public ModelAndView doSignup(

            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("redirect:/profile");

        return mv;
    }

    // PROFILE PAGE
    @RequestMapping(value = "/profile",
            method = RequestMethod.GET)
    public ModelAndView showProfile() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("profile");

        mv.addObject("username",
                "Gitanjan Debnath");

        mv.addObject("imageurl",
                "https://i.pravatar.cc/150");

        mv.addObject("designation",
                "Java Developer");

        return mv;
    }
}