package com.example.security.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {
    @Serial
    private final static long serialVersionUID = -23513532L;

    private String username;
    private String password;
}
