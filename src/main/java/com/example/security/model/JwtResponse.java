package com.example.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {
    @Serial
    private final static long serialVersionUID = -4124124124412L;

    private final String token;
}
