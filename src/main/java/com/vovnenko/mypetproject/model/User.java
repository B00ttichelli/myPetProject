package com.vovnenko.mypetproject.model;


import com.vovnenko.mypetproject.Enum.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username cant be blank")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password is required field")
    private String password;


    private Instant created;

    private String refreshTokenKey;

    private boolean enabled;
    private ROLE role = ROLE.ROLE_USER;

}
