package com.github.duskmage2009.phonecontacts.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ContactResponseDTO {
    Long id;
    String name;
    List<EmailResponseDTO> emailDTOS;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactResponseDTO that = (ContactResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    List<PhoneNumberResponseDTO> phoneNumberDTOS;
}
