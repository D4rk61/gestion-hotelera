package com.gestionhotel.persistance.repository.pagin;

import com.gestionhotel.persistance.entity.hotel.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface IHotelPaginRepository extends ListPagingAndSortingRepository<Hotel, Long> {

    @Query("SELECT DISTINCT h FROM Hotel h JOIN h.habitaciones ha WHERE ha.precio = ?1")
    Page<Hotel> findByPrecio(Pageable pageable, BigDecimal precio);

    // Buscar hoteles por cantidad de habitaciones
    @Query("SELECT h FROM Hotel h JOIN h.habitaciones ha GROUP BY h HAVING COUNT(ha) >= ?1")
    Page<Hotel> findByCantidadHabitaciones(Pageable pageable, Integer cantidadHabitaciones);

    Page<Hotel> findAll(Pageable pageable);

    Page<Hotel> findByEstrellas(Pageable pageable, Integer cantidadEstrellas);

}
