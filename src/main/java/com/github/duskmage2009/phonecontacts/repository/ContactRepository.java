package com.github.duskmage2009.phonecontacts.repository;

import com.github.duskmage2009.phonecontacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {

}
