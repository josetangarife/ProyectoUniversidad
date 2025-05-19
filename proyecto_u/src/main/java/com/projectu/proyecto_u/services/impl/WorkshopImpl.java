package com.projectu.proyecto_u.services.impl;

// @Service // Desactivada temporalmente

import org.springframework.beans.factory.annotation.Autowired;

import com.projectu.proyecto_u.models.dao.WorkshopDao;
import com.projectu.proyecto_u.models.entity.Workshop;
// import com.projectu.proyecto_u.services.IWorkshop;

import org.springframework.transaction.annotation.Transactional;

// Clase desactivada: no está registrada como @Service ni implementa la interfaz
public class WorkshopImpl /* implements IWorkshop */ {

    @Autowired
    private WorkshopDao workshopdao;

    @Transactional 
    public Workshop save(Workshop workshop) {
        return workshopdao.save(workshop);
    }

    /*
    @Transactional(readOnly = true)
    public Workshop findById(String rut) {
        return workshopdao.findById(rut).orElse(null);
    }
    */

    @Transactional
    public void delete(Workshop workshop) {
        workshopdao.delete(workshop);
    }
}
