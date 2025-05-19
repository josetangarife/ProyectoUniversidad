package com.projectu.proyecto_u.models.dao;
import org.springframework.data.repository.CrudRepository;

import com.projectu.proyecto_u.models.entity.Customer;;

public interface CustomerDao extends CrudRepository<Customer, Long> {

}
