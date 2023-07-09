package com.github.duskmage2009.phonecontacts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL)
    private List<Email> emails;
    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

    public void setEmails(List<Email> emails) {
        emails.forEach(email -> email.setContact(this));
        this.emails = emails;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        phoneNumbers.forEach(phoneNumber -> phoneNumber.setContact(this));
        this.phoneNumbers = phoneNumbers;
    }

}
