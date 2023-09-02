package com.gestionhotel.persistance.repository.crud;

import com.gestionhotel.persistance.entity.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteCrudRepository extends JpaRepository<Cliente, String> {


}
