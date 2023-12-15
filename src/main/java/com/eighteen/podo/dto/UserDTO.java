package com.eighteen.podo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private int id;
    private String email;
    private String password;
    private boolean is_receiver;
    private int login_failed_count;
    private String card_number;
    private String nick_name;
    private Status status;

    public enum Status {
        VALID, INVALID;
    }
}
