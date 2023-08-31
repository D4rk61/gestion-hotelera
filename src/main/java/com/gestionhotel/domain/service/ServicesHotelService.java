package com.gestionhotel.domain.service;

import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.entity.hotel.ServicesHotel;
import com.gestionhotel.persistance.repository.crud.IHotelCrudRepository;
import com.gestionhotel.persistance.repository.crud.IServicesHotelCrudRepository;
import com.gestionhotel.persistance.repository.pagin.IHotelPaginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesHotelService {

    @Autowired
    private final IServicesHotelCrudRepository  iServicesHotelCrudRepository;

    @Autowired
    private final IHotelCrudRepository iHotelCrudRepository;

    @Autowired
    private final IHotelPaginRepository  iHotelPaginRepository;

    public ServicesHotelService(IServicesHotelCrudRepository iServicesHotelCrudRepository, IHotelCrudRepository iHotelCrudRepository, IHotelPaginRepository iHotelPaginRepository) {
        this.iServicesHotelCrudRepository = iServicesHotelCrudRepository;
        this.iHotelCrudRepository = iHotelCrudRepository;
        this.iHotelPaginRepository = iHotelPaginRepository;
    }

    // agregar un servicio a un hotel
    public void addServiceHotel(Long idHotel, Long idService) {

        Optional<Hotel> hotel = iHotelCrudRepository.findById(idHotel);
        Optional<ServicesHotel> service = iServicesHotelCrudRepository.findById(idService);

        if(hotel.isPresent() && service.isPresent()) {
            hotel.get().getServices().add(service.get());
            iHotelCrudRepository.save(hotel.get());
        }
    }

    // eliminar un servicio de un hotel

    public void removeServiceFromHotel(Long idHotel, Long idService) {

        Optional<Hotel> hotel = iHotelCrudRepository.findById(idHotel);
        Optional<ServicesHotel> service = iServicesHotelCrudRepository.findById(idService);

        if(hotel.isPresent() && service.isPresent()) {
            hotel.get().getServices().remove(service.get());
            iHotelCrudRepository.save(hotel.get());
        }
    }


    // obtener todos los servicios de un hotel
    public Page<ServicesHotel> findAllByHotel(int page, int size, Long idHotel ) {

        Pageable pageable = PageRequest.of(page, size);
        return this.iHotelPaginRepository.findAllByServices(pageable, idHotel);
    }
}
