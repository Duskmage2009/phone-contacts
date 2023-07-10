package com.github.duskmage2009.phonecontacts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.duskmage2009.phonecontacts.dto.*;
import com.github.duskmage2009.phonecontacts.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    ContactResponseDTO contactResponseDTO;

    @MockBean
    private ContactService contactService;
    private ContactRequestDTO contactRequestDTO;

    private EmailResponseDTO emailResponseDTO;
    private PhoneNumberResponseDTO phoneNumberResponseDTO;
    private EmailDTO emailDTO;
    private PhoneNumberDTO phoneNumberDTO;

    @Test
    public void testAddValidPostStatusIsOk() throws Exception {

        emailResponseDTO = new EmailResponseDTO();
        emailResponseDTO.setEmail("Jenny@gmail.com");
        emailResponseDTO.setId(1l);
        List<EmailResponseDTO> emailResponseDTOList = new ArrayList<>();
        emailResponseDTOList.add(emailResponseDTO);


        phoneNumberResponseDTO = new PhoneNumberResponseDTO();
        phoneNumberResponseDTO.setPhone("1111");
        phoneNumberResponseDTO.setId(33l);
        List<PhoneNumberResponseDTO> phoneNumberResponseDTOS = new ArrayList<>();
        phoneNumberResponseDTOS.add(phoneNumberResponseDTO);
        contactResponseDTO = new ContactResponseDTO();
        contactResponseDTO.setName("Jenny");
        contactResponseDTO.setId(5l);
        contactResponseDTO.setEmailDTOS(emailResponseDTOList);
        contactResponseDTO.setPhoneNumberDTOS(phoneNumberResponseDTOS);

        emailDTO = new EmailDTO();
        emailDTO.setEmail("Jenny@gmail.com");
        phoneNumberDTO = new PhoneNumberDTO();
        phoneNumberDTO.setPhone("1111");
        List<EmailDTO> emailDTOS = new ArrayList<>();
        emailDTOS.add(emailDTO);
        List<PhoneNumberDTO> phoneNumberDTOS = new ArrayList<>();
        phoneNumberDTOS.add(phoneNumberDTO);

        contactRequestDTO = new ContactRequestDTO();
        contactRequestDTO.setName("Jenny");
        contactRequestDTO.setEmailDTOS(emailDTOS);
        contactRequestDTO.setPhoneNumberDTOS(phoneNumberDTOS);
        Mockito.when(contactService.contactSave(Mockito.any())).thenReturn(contactResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/contacts")
                        .content(objectMapper.writeValueAsString(contactRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testValidGetStatusIsOk() throws Exception {
        emailResponseDTO = new EmailResponseDTO();
        emailResponseDTO.setEmail("Jenny@gmail.com");
        emailResponseDTO.setId(1l);
        List<EmailResponseDTO> emailResponseDTOList = new ArrayList<>();
        emailResponseDTOList.add(emailResponseDTO);


        phoneNumberResponseDTO = new PhoneNumberResponseDTO();
        phoneNumberResponseDTO.setPhone("1111");
        phoneNumberResponseDTO.setId(33l);
        List<PhoneNumberResponseDTO> phoneNumberResponseDTOS = new ArrayList<>();
        phoneNumberResponseDTOS.add(phoneNumberResponseDTO);
        contactResponseDTO = new ContactResponseDTO();
        contactResponseDTO.setName("Jenny");
        contactResponseDTO.setId(5l);
        contactResponseDTO.setEmailDTOS(emailResponseDTOList);
        contactResponseDTO.setPhoneNumberDTOS(phoneNumberResponseDTOS);

        emailDTO = new EmailDTO();
        emailDTO.setEmail("Jenny@gmail.com");
        phoneNumberDTO = new PhoneNumberDTO();
        phoneNumberDTO.setPhone("1111");
        List<EmailDTO> emailDTOS = new ArrayList<>();
        emailDTOS.add(emailDTO);
        List<PhoneNumberDTO> phoneNumberDTOS = new ArrayList<>();
        phoneNumberDTOS.add(phoneNumberDTO);

        contactRequestDTO = new ContactRequestDTO();
        contactRequestDTO.setName("Jenny");
        contactRequestDTO.setEmailDTOS(emailDTOS);
        contactRequestDTO.setPhoneNumberDTOS(phoneNumberDTOS);
        Mockito.when(contactService.getContactById(Mockito.any())).thenReturn(contactResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contacts/1")

                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}