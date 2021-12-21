package com.vovnenko.mypetproject.service;

import com.vovnenko.mypetproject.dto.UserDto;

public interface UserService {
    UserDto getUserById(Long id);

}
