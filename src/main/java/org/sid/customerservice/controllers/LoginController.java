package org.sid.customerservice.controllers;

import org.sid.customerservice.entity.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/List")
    public String GetLoginPage(Model model) {
        List<Customer> listC = customerRepository.findAll();
        model.addAttribute("listC", listC);
        return "List";
    }

    @GetMapping("/template")
    public String GetMenuPage(){
        return "template";
    }

    @GetMapping("/")
    public String HomePage(){
        return "template";
    }

}
