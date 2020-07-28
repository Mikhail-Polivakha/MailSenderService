package com.example.MailSenderService.services;

import com.example.MailSenderService.models.User;
import com.example.MailSenderService.models.UserDTO;
import com.example.MailSenderService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserResolverService {

    UserRepository userRepository;

    @Autowired
    public UserResolverService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean sendGreetingEmailForJoiningTheCompany(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow();
        return false;
    }
}
