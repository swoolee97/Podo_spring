package com.eighteen.podo.service;

import com.eighteen.podo.dto.UserDTO;
import com.eighteen.podo.mapper.UserMapper;
import com.eighteen.podo.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public UserDTO login(String email, String password) {
        String cryptedPassword = EncryptUtil.bcrypt(password);
        UserDTO userDTO = userMapper.findByEmailAndPassword(email, cryptedPassword);
        return userDTO;
    }
}
