package com.vovnenko.mypetproject.security.controller;

import com.vovnenko.mypetproject.security.dto.LoginRequestDto;
import com.vovnenko.mypetproject.security.dto.RefreshTokenDto;
import com.vovnenko.mypetproject.security.dto.RegisterRequest;
import com.vovnenko.mypetproject.security.dto.SuccessLoginDto;
import com.vovnenko.mypetproject.security.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j   // to delete
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);

        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public SuccessLoginDto login(@RequestBody LoginRequestDto dto) {
        return authService.login(dto);
    }


    @GetMapping("/refreshToken")

    public ResponseEntity<RefreshTokenDto> refreshToken(@RequestParam @NotBlank String refreshToken) {
        log.info(" FUCKING TOKEN IS REFRESHED");
        return new ResponseEntity<>(authService.refreshToken(refreshToken), HttpStatus.OK);
    }



    @GetMapping("/logout")
    public ResponseEntity<Void> userLogOut() {

        

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
