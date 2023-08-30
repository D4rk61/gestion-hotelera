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
@Table(name = "category_hotel")

public class CategoryHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(name = "category_name", nullable = false, length = 50)
    private String name;

    /*
        BOUTIQUE,
        RESORT,
        BUSINESS,
        MOTEL,
        STANDARD
     */
}
