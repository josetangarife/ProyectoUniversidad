package com.projectu.proyecto_u.services;

import com.projectu.proyecto_u.models.entity.Customer;

public interface ICustomer {

    Customer save(Customer customer);

    Customer findById(Integer id);

    void delete(Customer customer);
}
