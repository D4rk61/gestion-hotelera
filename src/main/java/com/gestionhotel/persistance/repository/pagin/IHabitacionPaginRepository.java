package com.gestionhotel.persistance.repository.pagin;

import com.gestionhotel.persistance.entity.habitacion.Habitacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHabitacionPaginRepository extends ListPagingAndSortingRepository<Habitacion, Long> {

    Page<Habitacion> findAll(Pageable pageable);
}
