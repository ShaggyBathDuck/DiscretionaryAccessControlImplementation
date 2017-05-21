package com.bsk.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "login")
    private String login;
    @Column(name = "haslo")
    private String password;
    @Column(name="email")
    private String email;

    @Transient
    @OneToMany(mappedBy = "grantPrivilegePK.receiver")
    private List<GrantPrivilege> grantedPrivileges;
}
