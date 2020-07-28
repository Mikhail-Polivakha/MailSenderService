package com.example.MailSenderService.configuration;

import com.example.MailSenderService.models.UserDTO;
import com.example.MailSenderService.services.UserResolverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomEmailEventListenerAdapter {

    UserResolverService userResolverService;

    @Autowired
    public CustomEmailEventListenerAdapter(UserResolverService userResolverService) {
        this.userResolverService = userResolverService;
    }

    public void getUserFromQueueToSentEmail(UserDTO userDTO) throws IllegalArgumentException, NullPointerException {
        if (userDTO == null) {
            log.warn("passed null instead UserDTO object");
            throw new NullPointerException("passed null instead UserDTO object");
        } else if (userDTO.getAction() == null) {
            log.warn("passed null in UserDTO field 'ActionToPerform'");
            throw new IllegalArgumentException("passed null in UserDTO field 'ActionToPerform'");
        }

        switch (userDTO.getAction()) {
            case WELCOM_TO_COMPANY:
                userResolverService.sendGreetingEmailForJoiningTheCompany(userDTO);
                break;
        }
    }
}
