package com.gestionhotel.persistance.entity.habitacion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestionhotel.persistance.entity.hotel.Hotel;
import com.gestionhotel.persistance.entity.reserva.Reserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @Length(min = 2, max = 50)
    @Column(name = "numero", nullable = false, length = 50)
    private String numero;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;


    @Column(name = "capacidad", nullable = false, length = 50)
    private String capacidad;

    @NotNull
    private Float precio;
    @NotNull
    private boolean disponible = true;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    @ManyToMany(mappedBy = "habitaciones")
    private List<Reserva> reservas;

}