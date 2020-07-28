package com.example.MailSenderService.configuration;

import com.example.MailSenderService.models.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomEmailEventListenerAdapter {

    public void getUserFromQueueToSentEmail(UserDTO userDTO) throws IllegalArgumentException{
        if (userDTO == null) {
            log.warn("passed null instead UserDTO object");
            throw new IllegalArgumentException("passed null instead UserDTO object");
        }


    }
}
