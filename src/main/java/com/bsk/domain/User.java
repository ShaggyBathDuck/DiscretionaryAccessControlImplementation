package com.bsk.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Uzytkownicy")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "haslo")
    private String password;
    @Column(name="email")
    private String email;
}
