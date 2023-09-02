package com.gestionhotel.domain.service;

import com.gestionhotel.domain.service.email.EmailServiceImpl;
import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.repository.crud.IHotelCrudRepository;
import com.gestionhotel.persistance.repository.pagin.IHotelPaginRepository;
import com.gestionhotel.util.exception.EmptyHotelListException;
import com.gestionhotel.util.exception.HotelNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.Optional;

@Service        @Transactional
public class HotelService {

    @Autowired
    private final IHotelCrudRepository hotelCrudRepository;
    @Autowired
    private final IHotelPaginRepository hotelPaginRepository;
    @Autowired
    private final EmailServiceImpl emailService;
    public HotelService(IHotelCrudRepository hotelCrudRepository, IHotelPaginRepository hotelPaginRepository, EmailServiceImpl emailService) {
        this.hotelCrudRepository = hotelCrudRepository;
        this.hotelPaginRepository = hotelPaginRepository;
        this.emailService = emailService;
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


    // creacion de un nuevo hotel - area administrativa

    public Hotel save(Hotel newHotel) {
        try {
            // validaciones hechas ya con @Validation

            // guardar el hotel en una base de datos
            Hotel savedHotel = this.hotelCrudRepository.save(newHotel);

            // envio de email
            String to = savedHotel.getEmail();
            String subject = "¡Bienvenido/a a [nombre de tu sistema]!";

            String body = "Estimado/a personal de " + savedHotel.getName() + ",\n\n" +
                    "¡Gracias por registrarse en [nombre de tu sistema]! Estamos encantados de tenerlos como parte de nuestra comunidad.\n\n" +
                    "[Nombre de tu sistema] es el sistema de hotelería más completo y fácil de usar que les permite promocionar su oferta y atraer más clientes. Con [nombre de tu sistema] podrán:\n\n" +
                    "- Mostrar su hotel al mundo, con imágenes, descripciones, ubicación y opiniones.\n" +
                    "- Ofrecer sus habitaciones, servicios y otros beneficios a miles de usuarios de diferentes países de Latinoamérica.\n" +
                    "- Gestionar sus reservas, facturas, clientes y empleados con solo unos clics.\n" +
                    "- Disfrutar de ofertas exclusivas y descuentos especiales para nuestros socios.\n\n" +
                    "Para empezar a usar [nombre de tu sistema], solo tienen que ingresar a su cuenta con su nombre de usuario y contraseña. Si necesitan ayuda para usar el sistema, pueden consultar nuestro manual de usuario que les hemos adjuntado a este correo, donde encontrarán instrucciones paso a paso y consejos útiles.\n\n" +
                    "También les invitamos a visitar nuestro sitio web, donde podrán encontrar más información sobre nosotros, nuestros valores, nuestra misión y nuestra visión. Además, podrán acceder a nuestra guía de destinos, donde les ofrecemos recomendaciones e ideas para planificar su próximo viaje.\n\n" +
                    "Si tienen alguna pregunta, sugerencia o problema, no duden en contactarnos. Estamos aquí para ayudarles y hacer que su experiencia con [nombre de tu sistema] sea la mejor posible. Pueden escribirnos a [tu dirección de email] o llamarnos al [tu número de teléfono].\n\n" +
                    "Esperamos que disfruten de [nombre de tu sistema] y que les sea útil para mejorar su negocio. Estamos a su disposición para lo que necesiten.\n\n" +
                    "Saludos cordiales,\n\n" +
                    "[Tu nombre]\n" +
                    "[Tu cargo]\n";

            File file = new File("/home/blackshark/IdeaProjects/gestion-hotel/extras/Manual_de_uso.pdf");       // ruta del archivo adjunto
            emailService.sendEmailWithFile(to, subject, body, file);
            return savedHotel;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // crea el metodo update similar al de crear, con validaciones

    public Hotel update(Long id, Hotel updateHotel) {

        try {
            if(this.hotelCrudRepository.existsById(id)) {
                throw new HotelNotFoundException(id);
            }

            if(this.hotelCrudRepository.findById(id).isEmpty()) {
                throw new HotelNotFoundException(id);
            }

            return this.hotelCrudRepository.save(updateHotel);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void deleteById(Long id) {
        try {
            if(this.hotelCrudRepository.existsById(id)) {
                throw new HotelNotFoundException(id);
            }

            if(this.hotelCrudRepository.findById(id).isEmpty()) {
                throw new HotelNotFoundException(id);
            }

            this.hotelCrudRepository.deleteById(id);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
