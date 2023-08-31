package com.gestionhotel.persistance.repository.crud;

import com.gestionhotel.persistance.entity.hotel.ServicesHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicesHotelCrudRepository extends JpaRepository<ServicesHotel, Long> {

}
