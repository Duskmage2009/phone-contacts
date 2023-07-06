package com.github.duskmage2009.phonecontacts.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactResponseDTO {
    Long id;
    String name;
    List<EmailDTO> emailDTOS;
    List<PhoneNumberDTO> phoneNumberDTOS;
}
