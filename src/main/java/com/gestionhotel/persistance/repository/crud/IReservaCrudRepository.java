package com.gestionhotel.persistance.repository.crud;

import com.gestionhotel.persistance.entity.reserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservaCrudRepository extends JpaRepository<Reserva, Long> {

        @Query("SELECT h.name FROM Reserva r " +
                "INNER JOIN r.habitaciones ha " +
                "INNER JOIN ha.hotel h " +
                "INNER JOIN r.cliente c " +
                "WHERE r.id = :reservaId " +
                "AND c.dni = :clienteDni")
        String findHotelNameByReservaIdAndClienteDni(Long reservaId, String clienteDni);


    @Query("SELECT r.cliente.dni FROM Reserva r WHERE r.id = :reservaId")
    String findDniByReservaId(Long reservaId);

    @Query("SELECT h.telefono FROM Reserva r " +
            "INNER JOIN r.habitaciones ha " +
            "INNER JOIN ha.hotel h " +
            "WHERE r.id = :reservaId")
    String findTelefonoHotelByReservaId(Long reservaId);

    @Query("SELECT h.email FROM Reserva r " +
            "INNER JOIN r.habitaciones ha " +
            "INNER JOIN ha.hotel h " +
            "WHERE r.id = :reservaId")
    String findEmailHotelByReservaId(Long reservaId);

    // direccion del hotel
    @Query("SELECT h.direccion FROM Reserva r " +
            "INNER JOIN r.habitaciones ha " +
            "INNER JOIN ha.hotel h " +
            "WHERE r.id = :reservaId")
    String findDireccionHotelByReservaId(Long reservaId);


    // obtener datos de la habitacion


    // obtener el email del cliente con el reserva_id
    @Query("SELECT c.email FROM Reserva r " +
            "INNER JOIN r.cliente c " +
            "WHERE r.id = :reservaId")
    String findEmailClienteByReservaId(Long reservaId);
}
