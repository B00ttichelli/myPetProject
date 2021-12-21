package com.vovnenko.mypetproject.mapper;

import com.vovnenko.mypetproject.dto.UserDto;
import com.vovnenko.mypetproject.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto userToUserDto(User user);
}
