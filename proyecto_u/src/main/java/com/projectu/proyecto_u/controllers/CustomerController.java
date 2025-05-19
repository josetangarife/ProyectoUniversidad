package com.projectu.proyecto_u.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectu.proyecto_u.models.entity.Customer;
import com.projectu.proyecto_u.services.ICustomer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
    



@RestController
@RequestMapping("api/v1")
public class CustomerController {

   /*  @Autowired
    
    private ICustomer customerService;

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer){

        return customerService.save(customer);
    }
    @PutMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer update(@RequestBody Customer customer){

        return customerService.save(customer);
    }
    @DeleteMapping("customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Customer customerDelete = customerService.findById(id);
        customerService.delete(customerDelete);
    }

    @GetMapping("customer/{id}")   
    @ResponseStatus(HttpStatus.OK)
    public Customer showById(@PathVariable Integer id){
        return customerService.findById(id);
    }*/
}
