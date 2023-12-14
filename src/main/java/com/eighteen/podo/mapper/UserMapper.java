package com.eighteen.podo.mapper;

import com.eighteen.podo.dto.UserDTO;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    public ArrayList<String> findAll();

    public UserDTO findByEmailAndPassword(
            @Param("email") String email, @Param("password") String password
    );

}
