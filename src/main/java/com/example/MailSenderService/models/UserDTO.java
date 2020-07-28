package com.example.MailSenderService.models;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private ActionToPerform action;
}
