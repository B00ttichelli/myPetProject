package com.vovnenko.mypetproject.controller;

import com.vovnenko.mypetproject.dto.UserDto;
import com.vovnenko.mypetproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
 ResponseEntity<UserDto> getUserById(@RequestParam Long id){
     return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
 }
}
