package com.gestionhotel.persistance.repository.crud;

import com.gestionhotel.persistance.entity.hotel.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface IHotelCrudRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByName(String name);
    // buscar por latitud y longitud
    Optional<Hotel> findByLatitudAndLongitud(BigDecimal latitud, BigDecimal longitud);
    Optional<Hotel> findByLatitudBetweenAndLongitudBetween(BigDecimal minLat, BigDecimal maxLat, BigDecimal minLon, BigDecimal maxLon);
    Optional<Hotel> findByCategoryId(Long id);


}
