package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Uzytkownicy")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    Integer id;
    @Column(name = "login")
    String login;
    @Column(name = "haslo")
    String password;
    @Column(name="email")
    String email;
}
