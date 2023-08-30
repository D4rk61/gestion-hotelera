package com.gestionhotel.web.controller;

import com.gestionhotel.domain.service.HotelService;
import com.gestionhotel.persistance.entity.hotel.Hotel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private static final String Page_NUMBER = "0";
    private static final String Page_SIZE = "10";

    @Autowired
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Hotel>> findAll(
            @RequestParam(defaultValue = Page_NUMBER) int page,
            @RequestParam(defaultValue = Page_SIZE) int size
    ) {
        return ResponseEntity.ok(this.hotelService.findAll(page, size));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<Hotel>> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.hotelService.findById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/listByPrice")
    public ResponseEntity<Page<Hotel>> findByHabitacionPrecio(

            @RequestParam BigDecimal price,
            @RequestParam(defaultValue = Page_NUMBER) int page,
            @RequestParam(defaultValue = Page_SIZE) int size) {

        return ResponseEntity.ok(this.hotelService.findByHabitacionPrecio(page, size, price));

    }

    @GetMapping("/findByCantidadHabitaciones")
    public ResponseEntity<Page<Hotel>> findByCantidadHabitaciones(
            @RequestParam Integer cantidadHabitaciones,
            @RequestParam(defaultValue = Page_NUMBER) int page,
            @RequestParam(defaultValue = Page_SIZE) int size) {

        return ResponseEntity.ok(this.hotelService.findByCantidadHabitaciones(page, size, cantidadHabitaciones));

    }

    @GetMapping("/listByName")
    public ResponseEntity<Optional<Hotel>> findByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(this.hotelService.findByName(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/listByEstrellas")
    public ResponseEntity<Page<Hotel>> findByEstrellas(
            @RequestParam Integer cantidadEstrellas,
            @RequestParam(defaultValue = Page_NUMBER) int page,
            @RequestParam(defaultValue = Page_SIZE) int size) {
        try {
            return ResponseEntity.ok(this.hotelService.findByEstrellas(page, size, cantidadEstrellas));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/findBydireccion")

    public ResponseEntity<Optional<Hotel>> findBydireccion(
            @RequestParam BigDecimal latitud,
            @RequestParam BigDecimal longitud) {
        try {
            return ResponseEntity.ok(this.hotelService.findByLatitudAndLongitud(latitud, longitud));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

