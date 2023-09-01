package com.gestionhotel.persistance.repository.pagin;

import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.entity.hotel.ServicesHotel;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicesHotelPaginRepository extends ListPagingAndSortingRepository<ServicesHotel, Long> {
    Page<ServicesHotel> findAll(Pageable pageable);
}
