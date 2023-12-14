package com.eighteen.podo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String email;
    private String password;
    private Status status;

    public enum Status {
        VALID, INVALID;
    }
}
