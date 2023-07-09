package com.github.duskmage2009.phonecontacts.service;

import com.github.duskmage2009.phonecontacts.dto.ContactRequestDTO;
import com.github.duskmage2009.phonecontacts.dto.ContactResponseDTO;
import com.github.duskmage2009.phonecontacts.entity.Contact;
import com.github.duskmage2009.phonecontacts.mapper.ContactMapper;
import com.github.duskmage2009.phonecontacts.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    public List<ContactResponseDTO> getListDTO() {
//        List<Contact> contacts= contactRepository.findAll();
//        List<ContactResponseDTO> list = new ArrayList<>();
//        for (int i = 0; i <contacts.size(); i++) {
//            ContactResponseDTO contactResponseDTOS = contactMapper.mapContactResponse(contacts.get(i));
//            list.add(contactResponseDTOS);
//        }
//        return list;
        return contactRepository.findAll().stream()
                .map(contactMapper::mapContactResponse)
                .toList();

    }

    public ContactResponseDTO getContactById(Long id) {
//        Optional<Contact> optional = contactRepository.findById(id);
//        Contact contact = optional.get();
//        ContactResponseDTO contactResponseDTO = contactMapper.mapContactResponse(contact);
//        return contactResponseDTO;
        //return contactRepository.findById(id).map(contactMapper::mapContactResponse).orElseThrow();
      return   contactRepository.findById(id).map(new Function<Contact, ContactResponseDTO>() {
            @Override
            public ContactResponseDTO apply(Contact contact) {
                return contactMapper.mapContactResponse(contact);
            }
        }).orElseThrow();

    }


    public boolean delete(Long id) {
//        Optional<Contact> optional = contactRepository.findById(id);
//        Contact contact = optional.get();
//        contactRepository.delete(contact);
//        return true;
         return  contactRepository.findById(id).map(new Function<Contact, Boolean>() {
             @Override
             public Boolean apply(Contact contact) {
                 contactRepository.delete(contact);
                 return true;
             }
         }).orElse(false);
    }
    public ContactResponseDTO contactSave(ContactRequestDTO contactRequestDTO) {
        Contact contact = contactMapper.mapContact(contactRequestDTO);
        Contact savedContact = contactRepository.save(contact);
        ContactResponseDTO contactResponseDTO = contactMapper.mapContactResponse(savedContact);
        return contactResponseDTO;
    }
    public ContactResponseDTO update(long id,ContactResponseDTO contactResponseDTO){
        return contactRepository.findById(id).map(contact -> contactMapper.mapResponseToContact(contactResponseDTO))
                .map(contactRepository::saveAndFlush).map(contactMapper::mapContactResponse).orElseThrow();
//        Contact contact1 = contactMapper.mapResponseToContact(contactResponseDTO);
//        Contact putContact = contactRepository.save();

    }
}