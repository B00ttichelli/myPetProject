package com.vovnenko.mypetproject.security.controller;

import com.vovnenko.mypetproject.security.dto.LoginRequestDto;
import com.vovnenko.mypetproject.security.dto.RegisterRequest;
import com.vovnenko.mypetproject.security.dto.SuccessLoginDto;
import com.vovnenko.mypetproject.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public SuccessLoginDto login(@RequestBody LoginRequestDto dto) {
        return authService.login(dto);
    }


}
