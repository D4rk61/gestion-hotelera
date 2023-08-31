package com.gestionhotel.web.controller;

import com.gestionhotel.domain.service.ServicesHotelService;
import com.gestionhotel.persistance.entity.hotel.ServicesHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services/hotel")
public class ServicesHotelController {

    private static final String Page_NUMBER = "0";
    private static final String Page_SIZE = "10";

    @Autowired
    private final ServicesHotelService servicesHotelService;

    public ServicesHotelController(ServicesHotelService servicesHotelService) {
        this.servicesHotelService = servicesHotelService;
    }

    @GetMapping("/listAllBy/{id}")
    public ResponseEntity<?> findAllServicesByHotelId(@PathVariable  Long id,
      @RequestParam(defaultValue = Page_NUMBER) int page,
      @RequestParam(defaultValue = Page_SIZE) int size) {

        try {
            return ResponseEntity.ok(this.servicesHotelService.findAllByHotel(page, size, id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addService")
    public ResponseEntity<?> addService(@RequestParam Long idHotel, Long idService) {
        try {
            this.servicesHotelService.addServiceHotel(idHotel, idService);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleteService")
    public ResponseEntity<?> deleteService(@RequestParam Long idHotel, Long idService) {
        try {
            this.servicesHotelService.removeServiceFromHotel(idHotel, idService);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
