package com.github.duskmage2009.phonecontacts.mapper;

import com.github.duskmage2009.phonecontacts.dto.*;
import com.github.duskmage2009.phonecontacts.entity.Contact;
import com.github.duskmage2009.phonecontacts.entity.Email;
import com.github.duskmage2009.phonecontacts.entity.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactMapper {
    public Contact mapContact(ContactRequestDTO contactRequestDTO) {

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
    public ContactResponseDTO mapContactResponse(Contact contact) {
        ContactResponseDTO contactResponseDTO = new ContactResponseDTO();
        contactResponseDTO.setId(contact.getId());
        contactResponseDTO.setName(contact.getName());
        List<EmailResponseDTO> emailDTOS = contact.getEmails().stream()
                .map(email -> {
                    EmailResponseDTO emailDTO = new EmailResponseDTO();
                    emailDTO.setEmail(email.getEmail());
                    emailDTO.setId(email.getId());
                    return emailDTO;
                }).toList();

        List<PhoneNumberResponseDTO> phoneNumberDTOS = contact.getPhoneNumbers().stream()
                .map(phoneNumber -> {
                    PhoneNumberResponseDTO phoneNumberDTO = new PhoneNumberResponseDTO();
                    phoneNumberDTO.setPhone(phoneNumber.getPhone());
                    phoneNumberDTO.setId(phoneNumber.getId());
                    return phoneNumberDTO;
                }).toList();
        contactResponseDTO.setEmailDTOS(emailDTOS);
        contactResponseDTO.setPhoneNumberDTOS(phoneNumberDTOS);
        return contactResponseDTO;
    }
    public Contact mapResponseToContact(ContactResponseDTO contactResponseDTO){
        List<Email> emails = contactResponseDTO.getEmailDTOS().stream()
                .map(emailDTO -> {
                    Email email = new Email();
                    email.setEmail(emailDTO.getEmail());
                    email.setId(emailDTO.getId());
                    return email;
                }).toList();

        List<PhoneNumber> phoneNumbers = contactResponseDTO
                .getPhoneNumberDTOS().stream()
                .map(phoneNumberDTO ->
                {
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setPhone(phoneNumberDTO.getPhone());
                    phoneNumber.setId(phoneNumberDTO.getId());
                    return phoneNumber;
                }).toList();

        Contact contact = new Contact();
        contact.setId(contactResponseDTO.getId());
        contact.setName(contactResponseDTO.getName());
        contact.setEmails(emails);
        contact.setPhoneNumbers(phoneNumbers);
        return contact;
    }
}
