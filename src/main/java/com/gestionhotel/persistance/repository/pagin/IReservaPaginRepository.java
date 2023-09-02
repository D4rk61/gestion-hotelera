package com.gestionhotel.persistance.repository.pagin;

import com.gestionhotel.persistance.entity.reserva.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservaPaginRepository extends ListPagingAndSortingRepository<Reserva, Long> {

    Page<Reserva> findAll(Pageable pageable);
}
