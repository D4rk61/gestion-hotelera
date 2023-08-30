package com.gestionhotel.persistance.entity.hotel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor  @AllArgsConstructor
@Table(name = "services_hotel")
public class ServicesHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "services_id", nullable = false)
    private Long id;

    @Column(name = "services_hotel_name", nullable = false, length = 50)
    private String name;

    /*
    WIFI,
    ESTACIONAMIENTO,
    PISCINA,
    SPA,
    RESTAURANTE,
    GIMNASIO,
    SERVICIO_HABITACIONES,
    SERVICIO_LAVANDERIA,
    TRANSPORTE_AEROPUERTO,
    RECEPCION_24_HORAS,
    SERVICIO_CONCIERGE,
    SALA_REUNIONES,
    CENTRO_NEGOCIOS,
    SERVICIO_ALQUILER_AUTOS,
    SERVICIO_CUIDADO_NIÑOS,
    SERVICIO_LIMPIEZA_DIARIA,
    BAR_LOUNGE,
    JACUZZI,
    SAUNA,
    SERVICIO_DESPERTADOR,
    SERVICIO_MASCOTAS,
    SERVICIO_ALOJAMIENTO_MASCOTAS,
    SERVICIO_ALQUILER_BICICLETAS,
    SERVICIO_TRASLADO,
    SERVICIO_VALET,
    SERVICIO_PELUQUERIA,
    SERVICIO_CAJA_SEGURIDAD,
    ACTIVIDADES_RECREATIVAS,
    SERVICIO_INFORMACION_TURISTICA,
    SERVICIO_ENTRADAS_ESPECTACULOS,

     */
}
