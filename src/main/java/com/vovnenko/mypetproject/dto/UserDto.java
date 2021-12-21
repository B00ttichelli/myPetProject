package com.vovnenko.mypetproject.dto;

import com.vovnenko.mypetproject.Enum.ROLE;
import lombok.Data;

@Data
public class UserDto {


    private Long userId;

    private String username;

    private String role = ROLE.ROLE_USER.toString();

}
