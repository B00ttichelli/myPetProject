package com.vovnenko.mypetproject.controller;
import com.vovnenko.mypetproject.dto.AuthenticationResponse;
import com.vovnenko.mypetproject.dto.LoginRequest;
import com.vovnenko.mypetproject.dto.RegisterRequest;
import com.vovnenko.mypetproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);

        return new ResponseEntity<>("User registered with success", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){

         return authService.login(loginRequest);

    }

}
