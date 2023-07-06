package com.github.duskmage2009.phonecontacts.controller;

import com.github.duskmage2009.phonecontacts.dto.ContactRequestDTO;
import com.github.duskmage2009.phonecontacts.dto.ContactResponseDTO;
import com.github.duskmage2009.phonecontacts.dto.EmailDTO;
import com.github.duskmage2009.phonecontacts.dto.PhoneNumberDTO;
import com.github.duskmage2009.phonecontacts.entity.Contact;
import com.github.duskmage2009.phonecontacts.entity.Email;
import com.github.duskmage2009.phonecontacts.entity.PhoneNumber;
import com.github.duskmage2009.phonecontacts.repository.ContactRepository;
import com.github.duskmage2009.phonecontacts.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/contacts")

@RestController
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDTO save(@RequestBody ContactRequestDTO contactRequest) {
        return contactService.contactSave(contactRequest);

        //  return contactRepository.findById()
    }
}

