package com.gestionhotel.domain.service;

import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.repository.crud.IHotelCrudRepository;
import com.gestionhotel.persistance.repository.pagin.IHotelPaginRepository;
import com.gestionhotel.util.exception.EmptyHotelListException;
import com.gestionhotel.util.exception.HotelNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class HotelService {

    private final IHotelCrudRepository hotelCrudRepository;
    private final IHotelPaginRepository hotelPaginRepository;

    public HotelService(IHotelCrudRepository hotelCrudRepository, IHotelPaginRepository hotelPaginRepository) {
        this.hotelCrudRepository = hotelCrudRepository;
        this.hotelPaginRepository = hotelPaginRepository;
    }

    public Page<Hotel> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return this.hotelPaginRepository.findAll(pageable);
    }


    public Optional<Hotel> findById(Long id) {
        try {
            return this.hotelCrudRepository.findById(id);
        } catch (Exception e) {
            throw new HotelNotFoundException(id);
        }
    }

    public Page<Hotel> findByHabitacionPrecio(int page, int size, BigDecimal precio) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Hotel> hotelsPage = this.hotelPaginRepository.findByPrecio(pageable, precio);

        if (hotelsPage.isEmpty()) {
            throw new EmptyHotelListException();
        }
        return hotelsPage;
    }


    public Page<Hotel> findByCantidadHabitaciones(int page, int size, Integer cantidadHabitaciones) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Hotel> hotelsPage = this.hotelPaginRepository.findByCantidadHabitaciones(pageable, cantidadHabitaciones);

        if (hotelsPage.isEmpty()) {
            throw new EmptyHotelListException();
        }
        return hotelsPage;
    }


    public Optional<Hotel> findByName(String name) {
        try {
            return this.hotelCrudRepository.findByName(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Page<Hotel> findByEstrellas(int page, int size, Integer cantidadEstrellas) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Hotel> hotelsPage = this.hotelPaginRepository.findByEstrellas(pageable, cantidadEstrellas);

        if (hotelsPage.isEmpty()) {
            throw new EmptyHotelListException();
        }
        return hotelsPage;
    }

    public Optional<Hotel> findByLatitudAndLongitud(BigDecimal latitud, BigDecimal longitud) {

        try {
            return this.hotelCrudRepository.findByLatitudAndLongitud(latitud, longitud);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public Optional<Hotel> findSomethingLatitudAndLongitud(BigDecimal latitud, BigDecimal longitud) {

        try {
            return this.hotelCrudRepository.findByLatitudBetweenAndLongitudBetween(latitud, longitud);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

     */
}
