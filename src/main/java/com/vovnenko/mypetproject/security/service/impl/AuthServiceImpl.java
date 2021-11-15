package com.vovnenko.mypetproject.security.service.impl;

import com.vovnenko.mypetproject.Enum.ROLE;
import com.vovnenko.mypetproject.exceptions.CustomException;
import com.vovnenko.mypetproject.exceptions.WrongUserException;
import com.vovnenko.mypetproject.model.User;
import com.vovnenko.mypetproject.security.Jwt.JwtProvider;
import com.vovnenko.mypetproject.security.dto.LoginRequestDto;
import com.vovnenko.mypetproject.security.dto.RefreshTokenDto;
import com.vovnenko.mypetproject.security.dto.RegisterRequest;
import com.vovnenko.mypetproject.security.dto.SuccessLoginDto;
import com.vovnenko.mypetproject.security.repository.UserRepository;
import com.vovnenko.mypetproject.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(true);

        userRepository.save(user);
    }


    @Override
    public RefreshTokenDto refreshToken(String refreshToken) {
        String username;
        try {
            username = jwtProvider.getUsernameFromToken(refreshToken);
        } catch (Exception e) {
            throw new CustomException("Refresh token is invalid " + e.getMessage());
        }

        User user = userRepository.findByUsername(username).orElseThrow();

        //todo check user
        String refreshTokenUpdated = jwtProvider.createRefreshToken(user);
        userRepository.updateTokenKey(refreshTokenUpdated, user.getUserId());

        if (jwtProvider.isTokenValid(refreshToken, user.getRefreshTokenKey())) {
            user.setRefreshTokenKey(refreshTokenUpdated);
            return RefreshTokenDto.builder()
                    .accessToken(jwtProvider.createToken(username, user.getRole()))
                    .refreshToken(jwtProvider.createRefreshToken(user))
                    .build();

        } else {
            throw new CustomException("Refresh Token is nonValid");

        }


    }

    @Override
    public SuccessLoginDto login(LoginRequestDto dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new WrongUserException("User not founded"));
        if (!passwordChecker(dto, user)) {
            throw new WrongUserException("Passwords dont match,try again");
        }

        return SuccessLoginDto.builder()
                .userID(user.getUserId())
                .accessToken(jwtProvider.createToken(user.getUsername(), ROLE.ROLE_USER))
                .refreshToken(jwtProvider.createRefreshToken(user))
                .expiresAt(Instant.now().plusSeconds(7200))
                .name(user.getUsername())
                .build();

    }

    private boolean passwordChecker(LoginRequestDto dto,User user){
        return passwordEncoder.matches(dto.getPassword(), user.getPassword());
    }
}
