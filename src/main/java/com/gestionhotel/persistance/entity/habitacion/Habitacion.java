package com.gestionhotel.persistance.entity.habitacion;

import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.entity.reserva.Reserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor   @AllArgsConstructor
@Table(name = "habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitacion_id", nullable = false)
    private Long habitacionId;

    @Column(name = "numero", nullable = false, length = 50)
    private String numero;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "capacidad", nullable = false, length = 50)
    private String capacidad;

    @NotNull
    private BigDecimal precio;
    @NotNull
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToMany(mappedBy = "habitaciones")
    private List<Reserva> reservas;
}