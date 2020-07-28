package com.example.MailSenderService.configuration;

import com.example.MailSenderService.models.ActionToPerform;
import com.example.MailSenderService.models.UserDTO;
import com.example.MailSenderService.services.UserResolverService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomEmailEventListenerAdapterTest {

    @MockBean
    UserResolverService userResolverService;

    @InjectMocks
    CustomEmailEventListenerAdapter eventListenerAdapter;

    @BeforeAll
    void initialization() {
        userResolverService = Mockito.mock(UserResolverService.class);
    }

    @Test
    @DisplayName("Testing calling service which send the greeting email")
    void getUserFromQueueToSentEmailTest() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> eventListenerAdapter.getUserFromQueueToSentEmail(new UserDTO(1L,null)));

        Assertions.assertThrows(NullPointerException.class,
                () -> eventListenerAdapter.getUserFromQueueToSentEmail(null));

        UserDTO userDTO = new UserDTO(1L, ActionToPerform.WELCOM_TO_COMPANY);
        Mockito.when(userResolverService.sendGreetingEmailForJoiningTheCompany(userDTO)).thenReturn(true);

        Assertions.assertDoesNotThrow(
                () -> eventListenerAdapter.getUserFromQueueToSentEmail(userDTO));

        Mockito.verify(userResolverService).sendGreetingEmailForJoiningTheCompany(userDTO);
    }
}