package com.gestionhotel.web.controller;

import com.gestionhotel.domain.service.HabitacionService;
import com.gestionhotel.persistance.entity.habitacion.Habitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habitacion")
public class HabitacionController {

    private static final String Page_NUMBER = "0";
    private static final String Page_SIZE = "10";

    @Autowired
    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    public ResponseEntity<Page<Habitacion>> findAll(
            @RequestParam(defaultValue = Page_NUMBER) int page,
            @RequestParam(defaultValue = Page_SIZE) int size) {

        try {
            return ResponseEntity.ok(this.habitacionService.getAll(page, size));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
