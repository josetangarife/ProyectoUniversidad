package com.projectu.proyecto_u.services;

import com.projectu.proyecto_u.models.entity.Workshop;

public interface IWorkshop {

    Workshop save(Workshop workshop);

    Workshop findById(String rut);

    void delete(Workshop workshop);

}
