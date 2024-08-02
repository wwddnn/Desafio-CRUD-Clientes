package com.devsuperior.desafio_crud.controllers;
import com.devsuperior.desafio_crud.dto.ClientDTO;
import com.devsuperior.desafio_crud.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    //dependency
    @Autowired
    private ClientService service;

    // findById method
    // 200 ok()
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById (@PathVariable Long id) {
        ClientDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    //findAll method
    // 200 ok()
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll (Pageable pageable) {
        Page<ClientDTO> dto =  service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    //insert method
    // 201 created()
    //@Valid is a Bean Validation annotation
    @PostMapping
    public ResponseEntity<ClientDTO> insert (@Valid @RequestBody ClientDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    //update method
    // 200 ok()
    //@Valid is a Bean Validation annotation
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update (@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    //delete method
    // 204 no content()
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
