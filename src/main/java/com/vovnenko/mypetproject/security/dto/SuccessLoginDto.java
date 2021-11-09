package com.vovnenko.mypetproject.security.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SuccessLoginDto {
    private Long userID;
    private String accessToken;
    private String refreshToken;
    private String name;
}
