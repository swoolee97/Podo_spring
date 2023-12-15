package com.eighteen.podo.controller;

import com.eighteen.podo.service.MailService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/signUpCode")
    public void sendRandomCode(@RequestBody @NonNull MailAuthRequest mailAuthRequest) {
        String email = mailAuthRequest.getEmail();
        String randomCode = mailAuthRequest.getRandomCode();
        mailService.sendRandomCode(email, randomCode);
    }

    @Getter
    private static class MailAuthRequest {
        @NonNull
        String email;
        @NonNull
        String randomCode;
    }

}
