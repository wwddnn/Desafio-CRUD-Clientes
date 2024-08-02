package com.devsuperior.desafio_crud.services;
import com.devsuperior.desafio_crud.dto.ClientDTO;
import com.devsuperior.desafio_crud.entities.Client;
import com.devsuperior.desafio_crud.repositories.ClientRepository;
import com.devsuperior.desafio_crud.services.exceptions.NoSuchElementException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    //dependency
    @Autowired
    private ClientRepository repository;

    //findById method
    @Transactional(readOnly = true)
    public ClientDTO findById (Long id) {
        Client result = repository.findById(id).orElseThrow(()-> new NoSuchElementException("Id não encontrado"));
        ClientDTO dto = new ClientDTO(result);
        return dto;
    }

    //findAll method
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll (Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    //insert method
    @Transactional
    public ClientDTO insert (ClientDTO dto) {
            Client entity = new Client();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
    }

    //update method
    @Transactional
    public ClientDTO update (Long id, ClientDTO dto) {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }
        catch (EntityNotFoundException e) {
             throw new NoSuchElementException("Id não encontrado");
        }
    }

    //delete method
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete (Long id) {
        if(!repository.existsById(id)) {
            throw new NoSuchElementException("Id não encontrado");
        }
        repository.deleteById(id);
    }



    //method for copy dto to entity
    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
