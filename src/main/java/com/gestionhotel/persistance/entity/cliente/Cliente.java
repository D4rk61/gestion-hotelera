package com.gestionhotel.persistance.entity.cliente;

import com.gestionhotel.persistance.entity.reserva.Reserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity

@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "cliente_dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "cliente_nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "cliente_apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "cliente_telefono", nullable = false, length = 15)
    private String telefono;

    @Email
    @Column(name = "cliente_email", nullable = false, length = 60)
    private String email;

    @Column(name = "cliente_username", nullable = false, length = 50)
    private String username;
    @Column(name = "cliente_password", nullable = false, length = 60)
    private String password;

    private Boolean locked = false;
    private Boolean disabled = false;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "cliente_role", joinColumns = @JoinColumn(name = "username"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Cliente(String dni, String nombre, String apellido,
                   String telefono, String email, String username, String password, Set<Role> roles) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
        this.roles.add(Role.USER);
    }

    public Cliente() {

    }
    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;
}