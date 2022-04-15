package com.example.trains.mapper;

import com.example.trains.DTO.UserDTO.UserDTO;
import com.example.trains.domain.User;

import java.util.ArrayList;
import java.util.Set;

public class UserMapper {

    public ArrayList<UserDTO> getUserList(Iterable<User> userSet){
        var dtoList = new ArrayList<UserDTO>();
        for(User user : userSet){
            dtoList.add(getUserDTOFromEntity(user));
        }
        return dtoList;
    }

    public UserDTO getUserDTOFromEntity(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
    }
}
