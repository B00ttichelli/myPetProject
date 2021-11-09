package com.vovnenko.mypetproject.security.service;

import com.vovnenko.mypetproject.security.dto.LoginRequestDto;
import com.vovnenko.mypetproject.security.dto.RegisterRequest;
import com.vovnenko.mypetproject.security.dto.SuccessLoginDto;

public interface AuthService {
    void signup(RegisterRequest registerRequest);

    SuccessLoginDto login(LoginRequestDto dto);
}
