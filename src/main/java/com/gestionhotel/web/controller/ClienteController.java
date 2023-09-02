package com.gestionhotel.web.controller;

import com.gestionhotel.domain.service.ClienteService;
import com.gestionhotel.persistance.entity.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private static final String Page_NUMBER = "0";
    private static final String Page_SIZE = "10";

    @Autowired
    private final ClienteService  clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listAll")
    public ResponseEntity<Page<Cliente>> findAll(
            @RequestParam(name = "page", defaultValue = Page_NUMBER) int page,
            @RequestParam(name = "size", defaultValue = Page_SIZE) int size) {

        return ResponseEntity.ok(this.clienteService.getAll(page, size));
    }

    @GetMapping("/listById/{id}")
    public ResponseEntity<Optional<Cliente>> findById(@RequestParam String id) {
        try {
            return ResponseEntity.ok(this.clienteService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(this.clienteService.save(cliente));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
