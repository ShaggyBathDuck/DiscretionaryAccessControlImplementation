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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GrantPrivilege> getGrantedPrivileges() {
        return grantedPrivileges;
    }

    public void setGrantedPrivileges(List<GrantPrivilege> grantedPrivileges) {
        this.grantedPrivileges = grantedPrivileges;
    }
}
