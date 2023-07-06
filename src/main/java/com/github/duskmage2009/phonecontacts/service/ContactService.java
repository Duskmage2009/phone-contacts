package com.github.duskmage2009.phonecontacts.service;

import com.github.duskmage2009.phonecontacts.dto.ContactRequestDTO;
import com.github.duskmage2009.phonecontacts.dto.ContactResponseDTO;
import com.github.duskmage2009.phonecontacts.dto.EmailDTO;
import com.github.duskmage2009.phonecontacts.dto.PhoneNumberDTO;
import com.github.duskmage2009.phonecontacts.entity.Contact;
import com.github.duskmage2009.phonecontacts.entity.Email;
import com.github.duskmage2009.phonecontacts.entity.PhoneNumber;
import com.github.duskmage2009.phonecontacts.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
public ContactResponseDTO contactSave(ContactRequestDTO contactRequestDTO){
    Contact contact = mapContact(contactRequestDTO);
    Contact savedContact = contactRepository.save(contact);
    ContactResponseDTO contactResponseDTO = mapContactResponse(savedContact);
    return  contactResponseDTO;
}
    private Contact mapContact(ContactRequestDTO contactRequestDTO) {

        List<Email> emails = contactRequestDTO.getEmailDTOS().stream()
                .map(emailDTO -> {
                    Email email = new Email();
                    email.setEmail(emailDTO.getEmail());
                    return email;
                }).toList();

        List<PhoneNumber> phoneNumbers = contactRequestDTO
                .getPhoneNumberDTOS().stream()
                .map(phoneNumberDTO ->
                {
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setPhone(phoneNumberDTO.getPhone());
                    return phoneNumber;
                }).toList();

        Contact contact = new Contact();
        contact.setName(contactRequestDTO.getName());
        contact.setEmails(emails);
        contact.setPhoneNumbers(phoneNumbers);
        return contact;
    }

    private ContactResponseDTO mapContactResponse(Contact contact) {
        ContactResponseDTO contactResponseDTO = new ContactResponseDTO();
        contactResponseDTO.setId(contact.getId());
        contactResponseDTO.setName(contact.getName());
        List<EmailDTO> emailDTOS = contact.getEmails().stream()
                .map(email -> {
                    EmailDTO emailDTO = new EmailDTO();
                    emailDTO.setEmail(email.getEmail());
                    return emailDTO;
                }).toList();

        List<PhoneNumberDTO> phoneNumberDTOS = contact.getPhoneNumbers().stream()
                .map(phoneNumber -> {
                    PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
                    phoneNumberDTO.setPhone(phoneNumber.getPhone());
                    return phoneNumberDTO;
                }).toList();
        contactResponseDTO.setEmailDTOS(emailDTOS);
        contactResponseDTO.setPhoneNumberDTOS(phoneNumberDTOS);
        return contactResponseDTO;
    }
}