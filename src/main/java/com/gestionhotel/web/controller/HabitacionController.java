package com.gestionhotel.web.controller;

import com.gestionhotel.domain.service.HabitacionService;
import com.gestionhotel.persistance.entity.habitacion.Habitacion;
import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.repository.crud.IHotelCrudRepository;
import com.gestionhotel.util.exception.HotelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitacion")
public class HabitacionController {

    private static final String Page_NUMBER = "0";
    private static final String Page_SIZE = "10";

    @Autowired
    private final HabitacionService habitacionService;

    @Autowired
    private final IHotelCrudRepository  hotelCrudRepository;
    public HabitacionController(HabitacionService habitacionService, IHotelCrudRepository hotelCrudRepository) {
        this.habitacionService = habitacionService;
        this.hotelCrudRepository = hotelCrudRepository;
    }

    @GetMapping("/listAll")
    public ResponseEntity<Page<Habitacion>> findAll(
            @RequestParam(defaultValue = Page_NUMBER) int page,
            @RequestParam(defaultValue = Page_SIZE) int size) {

        try {
            return ResponseEntity.ok(this.habitacionService.getAll(page, size));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/listAllByType")
    public ResponseEntity<List<Habitacion>> getAllByTipoAndCapacidad(
            @RequestParam String tipo,
            @RequestParam String capacidad) {
        try {
            return ResponseEntity.ok(this.habitacionService.getAllByTipoAndCapacidad(tipo, capacidad));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("listAllByHotel")
    public ResponseEntity<Page<Habitacion>> findByHotel(@RequestParam Long hotelId,
                @RequestParam(defaultValue = Page_NUMBER) int page,
                @RequestParam(defaultValue = Page_SIZE) int size) {
        try {
            Hotel hotel = hotelCrudRepository.findById(hotelId).orElseThrow(() -> new HotelNotFoundException(hotelId));
            return ResponseEntity.ok(this.habitacionService.findByEspecificHotel(hotel, page, size));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/listAllByDisponible")
    public ResponseEntity<Page<Habitacion>> findByDisponibleTrue(
            @RequestParam(defaultValue = Page_NUMBER) int page,
            @RequestParam(defaultValue = Page_SIZE) int size) {
        try {
            return ResponseEntity.ok(this.habitacionService.findByDisponibleTrue(page, size));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // area de administracion - ADMIN

    @PostMapping("/save")
    public ResponseEntity<Habitacion> save(@RequestBody Habitacion habitacion) {
        try {
            return ResponseEntity.ok(this.habitacionService.save(habitacion));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Habitacion> update(@RequestBody Habitacion habitacion) {
        try {
            return ResponseEntity.ok(this.habitacionService.update(habitacion));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        try {
            this.habitacionService.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
