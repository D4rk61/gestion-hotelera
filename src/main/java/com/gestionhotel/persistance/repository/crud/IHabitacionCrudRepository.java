package com.gestionhotel.persistance.repository.crud;

import com.gestionhotel.persistance.entity.habitacion.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHabitacionCrudRepository extends JpaRepository<Habitacion, Long> {

}
