package com.gestionhotel.domain.service;

import com.gestionhotel.domain.service.email.EmailServiceImpl;
import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.repository.crud.IHotelCrudRepository;
import com.gestionhotel.persistance.repository.pagin.IHotelPaginRepository;
import com.gestionhotel.util.exception.EmptyHotelListException;
import com.gestionhotel.util.exception.HotelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.Optional;

@Service
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
            String subject = "Bienvenido a nuestro Sistema de Gestion de Hoteleria";

            String body = "Estimado/a personal de " + savedHotel.getName() + ",\n\n" +
                    "Le agradecemos por confiar en nosotros y registrarse en nuestro Sistema de Gestión de Hotelería. Aquí podrá mostrarse al mundo y ofrecer sus habitaciones, servicios y otros beneficios. Contamos con un amplio catálogo de usuarios de diferentes países de Latinoamérica, con sistema de geolocalización por Google Maps y sin ningún costo para usted.\n\n" +
                    "Estos son los datos que hemos registrado de su hotel:\n\n" +
                    "Nombre: " + savedHotel.getName() + "\n" +
                    "Dirección: " + savedHotel.getDireccion() + "\n" +
                    "Latitud: " + savedHotel.getLatitud() + "\n" +
                    "Longitud: " + savedHotel.getLongitud() + "\n" +
                    "Teléfono: " + savedHotel.getTelefono() + "\n" +
                    "Esperamos que disfrute de nuestro sistema y que le sea útil para mejorar su negocio.\n\n" +
                    "Saludos cordiales,\n" +
                    "El equipo de Gestión Hotelera";

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
