package com.gestionhotel.persistance.repository.pagin;

import com.gestionhotel.persistance.entity.hotel.CategoryHotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryHotelPaginRepository extends ListPagingAndSortingRepository<CategoryHotel, Long> {

    Page<CategoryHotel> findAll(Pageable pageable);
}
