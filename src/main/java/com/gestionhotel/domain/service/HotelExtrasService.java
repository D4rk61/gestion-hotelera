package com.gestionhotel.domain.service;

import com.gestionhotel.persistance.entity.hotel.CategoryHotel;
import com.gestionhotel.persistance.entity.hotel.ServicesHotel;
import com.gestionhotel.persistance.repository.crud.ICategoryHotelCrudRepository;
import com.gestionhotel.persistance.repository.crud.IServicesHotelCrudRepository;
import com.gestionhotel.persistance.repository.pagin.ICategoryHotelPaginRepository;
import com.gestionhotel.persistance.repository.pagin.IServicesHotelPaginRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service    @Transactional
public class HotelExtrasService {

    // servicios de hoteles
    @Autowired
    private final IServicesHotelCrudRepository iServicesHotelCrudRepository;
    @Autowired
    private final IServicesHotelPaginRepository  iServicesHotelPaginRepository;

    // Categoria de hoteles
    @Autowired
    private final ICategoryHotelPaginRepository  iCategoryHotelPaginRepository;
    @Autowired
    private final ICategoryHotelCrudRepository  iCategoryHotelCrudRepository;

    public HotelExtrasService(IServicesHotelCrudRepository iServicesHotelCrudRepository, IServicesHotelPaginRepository iServicesHotelPaginRepository, ICategoryHotelPaginRepository iCategoryHotelPaginRepository, ICategoryHotelCrudRepository iCategoryHotelCrudRepository) {
        this.iServicesHotelCrudRepository = iServicesHotelCrudRepository;
        this.iServicesHotelPaginRepository = iServicesHotelPaginRepository;
        this.iCategoryHotelPaginRepository = iCategoryHotelPaginRepository;
        this.iCategoryHotelCrudRepository = iCategoryHotelCrudRepository;
    }

    public Page<ServicesHotel> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iServicesHotelPaginRepository.findAll(pageable);
    }

    public Page<CategoryHotel> getAllCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iCategoryHotelPaginRepository.findAll(pageable);
    }
}
