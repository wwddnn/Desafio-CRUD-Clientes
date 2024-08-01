package com.devsuperior.desafio_crud.services;
import com.devsuperior.desafio_crud.dto.ClientDTO;
import com.devsuperior.desafio_crud.entities.Client;
import com.devsuperior.desafio_crud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    //dependency
    @Autowired
    private ClientRepository repository;

    //findById method
    @Transactional(readOnly = true)
    public ClientDTO findById (Long id) {
        Client result = repository.findById(id).get();
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
        Client entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    //delete method
    @Transactional
    public void delete (Long id) {
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
