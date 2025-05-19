/*package com.projectu.proyecto_u.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectu.proyecto_u.models.dao.CustomerDao;
import com.projectu.proyecto_u.models.entity.Customer;
import com.projectu.proyecto_u.services.ICustomer;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerImpl implements ICustomer {

    @Autowired
    private CustomerDao customerdao;

    @Transactional
    @Override
    public Customer save(Customer customer) {
       
        return customerdao.save(customer);
    }
    @Transactional(readOnly = true)
    @Override
    public Customer findById(Integer id) {
      
        return customerdao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Customer customer) {
       
        customerdao.delete(customer);
    }

}
*/