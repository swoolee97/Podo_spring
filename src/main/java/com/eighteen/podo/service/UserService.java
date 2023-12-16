package com.eighteen.podo.service;

import com.eighteen.podo.dto.UserDTO;
import com.eighteen.podo.exception.user.DuplicatedEmailException;
import com.eighteen.podo.mapper.UserMapper;
import com.eighteen.podo.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public UserDTO login(String email, String password) {
        UserDTO userDTO = userMapper.findUserByEmail(email);
        if (EncryptUtil.isMatch(password, userDTO.getPassword())) {
            return userDTO;
        }
        return null;
    }

    public void insertUser(UserDTO userDTO) {
        int isDuplicatedEmail = userMapper.emailCheck(userDTO.getEmail());
        if (isDuplicatedEmail == 1) {
            throw new DuplicatedEmailException("중복된 이메일입니다");
        }
        String cryptedPassword = EncryptUtil.bcrypt(userDTO.getPassword());
        userDTO.setPassword(cryptedPassword);
        userMapper.insertUser(userDTO);
    }
}
