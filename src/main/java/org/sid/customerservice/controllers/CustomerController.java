package org.sid.customerservice.controllers;

import org.sid.customerservice.entity.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    /*
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/Allcustomers")
    public  List<Customer>  getCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }
     */

}
