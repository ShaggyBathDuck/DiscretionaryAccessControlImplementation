package com.bsk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Uprawnienia")
public class Privilege {

    @Id
    @Column(name = "nip")
    private Long id;

    @Column(name = "stworz")
    private String create;

    @Column(name = "czytaj")
    private String read;

    @Column(name = "modyfikuj")
    private String update;

    @Column(name = "usun")
    private String delete;

    public Privilege(Long id, String create, String read, String update, String delete) {
        this.id = id;
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
    }

    public Privilege() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }
}
