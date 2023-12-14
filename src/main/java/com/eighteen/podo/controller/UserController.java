package com.eighteen.podo.controller;

import com.eighteen.podo.dto.UserDTO;
import com.eighteen.podo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> login(@RequestBody @NonNull UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
        UserDTO userDTO = userService.login(email, password);

        if (userDTO == null) {
            return new ResponseEntity<>(LoginResponse.fail, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(LoginResponse.success(userDTO), HttpStatus.OK);

    }

    @Getter
    private static class UserLoginRequest {
        @NonNull
        private String email;
        @NonNull
        private String password;
    }

    @AllArgsConstructor
    @RequiredArgsConstructor
    private static class LoginResponse {
        enum LoginStatus {
            SUCCESS, FAIL;
        }

        @NonNull
        private LoginStatus loginStatus;
        private UserDTO userDTO;

        private static final LoginResponse fail = new LoginResponse(LoginStatus.FAIL);

        private static final LoginResponse success(UserDTO userDTO) {
            return new LoginResponse(LoginStatus.SUCCESS, userDTO);
        }
    }

}
