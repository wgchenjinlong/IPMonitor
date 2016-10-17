package com.example.monitor.controllers.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView index() {

        return new ModelAndView("login/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login() {

        return new ModelAndView(new RedirectView("/monitor", true));
    }
}
