package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/database")
public class DatabaseController{
    @GetMapping(value = "/monitoring")
    public ModelAndView databaseMonitoring(){
        return new ModelAndView("database/monitoring");
    }
}
