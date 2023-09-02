package com.gestionhotel.domain.service;

import com.gestionhotel.persistance.entity.habitacion.Habitacion;
import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.repository.crud.IHabitacionCrudRepository;
import com.gestionhotel.persistance.repository.crud.IHotelCrudRepository;
import com.gestionhotel.persistance.repository.pagin.IHabitacionPaginRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service       @Transactional
public class HabitacionService {

    @Autowired
    private final IHabitacionCrudRepository habitacionCrudRepository;
    @Autowired
    private final IHabitacionPaginRepository  habitacionPaginRepository;

    @Autowired
    private final IHotelCrudRepository  hotelCrudRepository;
    public HabitacionService(IHabitacionCrudRepository habitacionCrudRepository, IHabitacionPaginRepository habitacionPaginRepository, IHotelCrudRepository hotelCrudRepository) {
        this.habitacionCrudRepository = habitacionCrudRepository;
        this.habitacionPaginRepository = habitacionPaginRepository;
        this.hotelCrudRepository = hotelCrudRepository;
    }


    public Page<Habitacion> getAll(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        return habitacionPaginRepository.findAll(pageable);
    }

    public List<Habitacion> getAllByTipoAndCapacidad(String tipo, String capacidad) {
        return habitacionCrudRepository.findByTipoAndCapacidad(tipo, capacidad);
    }

    public Page<Habitacion> findByEspecificHotel(Hotel hotel, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return habitacionPaginRepository.findByHotel(hotel, pageable);
    }

    public Page<Habitacion> findByDisponibleTrue(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return habitacionPaginRepository.findByDisponibleTrue(pageable);
    }

    public Habitacion save(Habitacion habitacion) {
        // validaciones
        if(habitacion.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        if(!hotelCrudRepository.existsById(habitacion.getHotel().getId())) {
            throw new RuntimeException("El hotel no existe");
        }

        return habitacionCrudRepository.save(habitacion);
    }

    public Habitacion update(Habitacion habitacion) {
        // validaciones
        if(habitacion.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        if(!hotelCrudRepository.existsById(habitacion.getHotel().getId())) {
            throw new RuntimeException("El hotel no existe");
        }

        return habitacionCrudRepository.save(habitacion);
    }


    public void delete(Long id) {
        if(!habitacionCrudRepository.existsById(id)) {
            throw new RuntimeException("La habitacion no existe");
        }

        this.habitacionCrudRepository.deleteById(id);
    }

}
