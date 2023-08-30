package com.gestionhotel.persistance.entity.hotel;

import com.gestionhotel.persistance.entity.habitacion.Habitacion;
import com.gestionhotel.persistance.entity.hotel.CategoryHotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id", nullable = false)
    private Long id;

    @Column(name = "hotel_name", nullable = false, length = 50)
    private String name;

    @Column(name = "hotel_descripcion", nullable = false, length = 400)
    private String descripcion;

    @Column(name = "hotel_estrellas_numero", nullable = false)
    private Integer estrellas;

    @Column(name = "hotel_direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "hotel_latitud", nullable = false, length = 50)
    private BigDecimal latitud;

    @Column(name = "hotel_longitud", nullable = false, length = 50)
    private BigDecimal longitud;

    @Column(name = "hotel_telefono", nullable = false, length = 50)
    private String telefono;

    @Email
    @Column(name = "hotel_email", nullable = false, length = 50)
    private String email;

    // relacion de uno a muchos con categoria
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryHotel category;

    // relacion de muchos a muchos con servicios
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "hotel_services",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "services_id")
    )
    private List<ServicesHotel> services;

    @OneToMany(mappedBy = "hotel")
    private List<Habitacion> habitaciones;
}