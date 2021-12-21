package com.vovnenko.mypetproject.service.impl;

import com.vovnenko.mypetproject.dto.UserDto;
import com.vovnenko.mypetproject.mapper.UserMapper;
import com.vovnenko.mypetproject.security.repository.UserRepository;
import com.vovnenko.mypetproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDto getUserById(Long id) {
        return userMapper.userToUserDto(userRepository.getById(id));
    }
}
