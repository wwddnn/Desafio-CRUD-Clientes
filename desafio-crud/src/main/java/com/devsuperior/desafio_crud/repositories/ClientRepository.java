package com.devsuperior.desafio_crud.repositories;
import com.devsuperior.desafio_crud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
