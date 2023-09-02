package com.gestionhotel.web.controller;

import com.gestionhotel.domain.service.ReservaService;
import com.gestionhotel.persistance.entity.reserva.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    private static final String Page_NUMBER = "0";
    private static final String Page_SIZE = "10";

    @Autowired
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Reserva>> findAll(
            @RequestParam(defaultValue = Page_NUMBER) int page,
            @RequestParam(defaultValue = Page_SIZE) int size
    ) {
        return ResponseEntity.ok(this.reservaService.getAll(page, size));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.reservaService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    public void create(@RequestBody Reserva reserva) {
        this.reservaService.crearReservaConCliente(reserva);

    }

}
