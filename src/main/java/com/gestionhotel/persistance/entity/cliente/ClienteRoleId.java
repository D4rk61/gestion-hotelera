package com.gestionhotel.persistance.entity.cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

@Embeddable
public class ClienteRoleId implements Serializable {

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    // Usando EnumType.STRING para almacenar el valor textual del rol
    @Column(name = "role", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Role role;
}
