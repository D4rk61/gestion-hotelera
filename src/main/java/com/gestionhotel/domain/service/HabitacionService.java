package com.gestionhotel.domain.service;

import com.gestionhotel.persistance.entity.habitacion.Habitacion;
import com.gestionhotel.persistance.repository.crud.IHabitacionCrudRepository;
import com.gestionhotel.persistance.repository.pagin.IHabitacionPaginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {

    @Autowired
    private final IHabitacionCrudRepository habitacionCrudRepository;
    @Autowired
    private final IHabitacionPaginRepository  habitacionPaginRepository;

    public HabitacionService(IHabitacionCrudRepository habitacionCrudRepository, IHabitacionPaginRepository habitacionPaginRepository) {
        this.habitacionCrudRepository = habitacionCrudRepository;
        this.habitacionPaginRepository = habitacionPaginRepository;
    }


    public Page<Habitacion> getAll(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        return habitacionPaginRepository.findAll(pageable);
    }

}
