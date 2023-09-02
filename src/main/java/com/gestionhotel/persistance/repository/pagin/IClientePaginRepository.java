package com.gestionhotel.persistance.repository.pagin;

import com.gestionhotel.persistance.entity.cliente.Cliente;
import com.gestionhotel.persistance.entity.habitacion.Habitacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientePaginRepository extends ListPagingAndSortingRepository<Cliente, String> {

    Page<Cliente> findAll(Pageable pageable);

    @Query("SELECT r.habitaciones FROM Cliente c JOIN c.reservas r WHERE c.dni = :dni")
    Page<Habitacion> findHabitacionesByDni(String dni, Pageable pageable);
}
