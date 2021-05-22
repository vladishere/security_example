package com.example.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity(name = "USR")
@Table(name = "USR")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "EMAIL")
    private String email;
}