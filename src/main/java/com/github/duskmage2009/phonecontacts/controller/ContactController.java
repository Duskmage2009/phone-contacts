package com.github.duskmage2009.phonecontacts.controller;

import com.github.duskmage2009.phonecontacts.dto.ContactRequestDTO;
import com.github.duskmage2009.phonecontacts.dto.ContactResponseDTO;
import com.github.duskmage2009.phonecontacts.entity.Contact;
import com.github.duskmage2009.phonecontacts.repository.ContactRepository;
import com.github.duskmage2009.phonecontacts.service.ContactService;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/contacts")

@RestController
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService, ContactRepository contactRepository) {
        this.contactService = contactService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDTO save(@RequestBody ContactRequestDTO contactRequest) {
        return contactService.contactSave(contactRequest);

    }

    @GetMapping("/{id}")
    public ContactResponseDTO getById(@PathVariable long id) {

        ContactResponseDTO contactResponseDTO = contactService.getContactById(id);
        return contactResponseDTO;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id ){
        if(!contactService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public List<ContactResponseDTO> getListContact(){
        return contactService.getListDTO();
    }
    @PutMapping("/{id}")
    public ContactResponseDTO update(@PathVariable long id,@RequestBody ContactResponseDTO contactResponseDTO){
        return contactService.update(id,contactResponseDTO);
    }
}

