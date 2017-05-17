package com.bsk.domain;

import javax.persistence.*;

@Entity(name = "Uprawnienia")
public class Privilege {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "stworz")
    private String create;

    @Column(name = "czytaj")
    private String read;

    @Column(name = "modyfikuj")
    private String update;

    @Column(name = "usun")
    private String delete;

    public Privilege() {
    }

    public Privilege(Integer id, String create, String read, String update, String delete) {
        this.id = id;
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
