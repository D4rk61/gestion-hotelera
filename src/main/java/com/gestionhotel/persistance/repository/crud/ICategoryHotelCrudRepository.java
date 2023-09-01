package com.gestionhotel.persistance.repository.crud;

import com.gestionhotel.persistance.entity.hotel.CategoryHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryHotelCrudRepository extends JpaRepository<CategoryHotel, Long> {
}
