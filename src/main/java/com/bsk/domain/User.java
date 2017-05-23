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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return grantedPrivileges != null ? grantedPrivileges.equals(user.grantedPrivileges) : user.grantedPrivileges == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (grantedPrivileges != null ? grantedPrivileges.hashCode() : 0);
        return result;
    }
}
