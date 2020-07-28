package com.example.MailSenderService.services;

import com.example.MailSenderService.models.ActionToPerform;
import com.example.MailSenderService.models.User;
import com.example.MailSenderService.models.UserDTO;
import com.example.MailSenderService.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserResolverServiceTest {

    @InjectMocks
    UserResolverService userResolverService;

    UserRepository userRepository;

    JavaMailSender javaMailSender;

    @BeforeAll
    void initialization() {
        userRepository = Mockito.mock(UserRepository.class);
        javaMailSender = Mockito.mock(JavaMailSender.class);
        userResolverService = new UserResolverService(userRepository, javaMailSender);
    }

    @Test
    @DisplayName("Testing sending the greeting email to the user")
    void sendGreetingEmailForJoiningTheCompany() {
        UserDTO userDTO = new UserDTO(1L, ActionToPerform.WELCOM_TO_COMPANY);
        User mockedUserFetchedFromDatabase = new User(
                "FirstName",
                "LastName",
                new Date(System.currentTimeMillis()),
                "mikhailpolivakha@gmail.com",
                1000L
        );

        Mockito.when(userRepository.findById(userDTO.getId()))
                .thenReturn(Optional.of(mockedUserFetchedFromDatabase));
//        Mockito.doNothing().when(javaMailSender.send());

        Assertions.assertTrue(userResolverService.sendGreetingEmailForJoiningTheCompany(userDTO));
        Mockito.verify(userRepository).findById(userDTO.getId());
    }
}