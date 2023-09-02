package com.gestionhotel.persistance.repository.crud;

import com.gestionhotel.persistance.entity.habitacion.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHabitacionCrudRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByTipoAndCapacidad(String tipo, String capacidad);


}
