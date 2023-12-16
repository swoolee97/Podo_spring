package com.eighteen.podo.controller;

import com.eighteen.podo.dto.UserDTO;
import com.eighteen.podo.exception.user.DuplicatedEmailException;
import com.eighteen.podo.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    private ResponseEntity<UserDTO> login(@RequestBody @NonNull UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
        UserDTO userDTO = userService.login(email, password);

        if (userDTO == null) {
            return new ResponseEntity<>(userDTO, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PostMapping("signUp")
    @ResponseStatus(HttpStatus.CREATED)
    private void signUp(@RequestBody UserDTO userDTO) {
        userService.insertUser(userDTO);
    }

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<String> handleDuplicatedEmail(Exception e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }

    @Getter
    private static class UserLoginRequest {
        @NonNull
        private String email;
        @NonNull
        private String password;
    }

}
