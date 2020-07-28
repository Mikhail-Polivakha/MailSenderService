package com.example.MailSenderService.services;

import com.example.MailSenderService.models.User;
import com.example.MailSenderService.models.UserDTO;
import com.example.MailSenderService.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserResolverService {

    UserRepository userRepository;

    JavaMailSender javaMailSender;

    @Autowired
    public UserResolverService(UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    public boolean sendGreetingEmailForJoiningTheCompany(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("MTC Company");
        mailMessage.setFrom("polivakhamisha48@gmail.com");
        mailMessage.setText("Thank you for joining us! \n " +
                "Hope our business working together might to you good)");
        mailMessage.setTo(user.getEmail());

        try {
            javaMailSender.send(mailMessage);
            log.info("Mail to user with id : {} was successfully sent", user.getId());
            return true;
        } catch (MailException e) {
            log.warn("Mail to user with id : {} was not sent", user.getId());
            return false;
        }
    }
}
