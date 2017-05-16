package com.bsk.domain;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "Uzytkownicy")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class User {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Size(min = 6)
    @Column(name = "login")
    private String login;

    @Size(min = 6)
    @Column(name = "haslo")
    private String password;

    @Email
    @Column(name="email")
    private String email;
}
