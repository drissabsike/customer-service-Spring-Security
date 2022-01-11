package org.sid.customerservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {

    @GetMapping("/notAuthorized")
    public String error(){
        return "notAuthorized";
    }

    @GetMapping("/login")
    public String GotToLoginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String GotToLogoutPage(HttpServletRequest httpServletRequest)throws ServletException {
        httpServletRequest.logout(); //pour quité la session
        return "redirect:/login";
    }

}
