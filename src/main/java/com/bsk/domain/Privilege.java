package com.bsk.domain;

import javax.persistence.*;

@Entity(name = "Uprawnienia")
public class Privilege {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Privilege privilege = (Privilege) o;

        if (create != null ? !create.equals(privilege.create) : privilege.create != null) return false;
        if (read != null ? !read.equals(privilege.read) : privilege.read != null) return false;
        if (update != null ? !update.equals(privilege.update) : privilege.update != null) return false;
        return delete != null ? delete.equals(privilege.delete) : privilege.delete == null;
    }

    @Override
    public int hashCode() {
        int result = create != null ? create.hashCode() : 0;
        result = 31 * result + (read != null ? read.hashCode() : 0);
        result = 31 * result + (update != null ? update.hashCode() : 0);
        result = 31 * result + (delete != null ? delete.hashCode() : 0);
        return result;
    }

    public boolean hasEffectiveRights() {
        if (create.equalsIgnoreCase("NONE") &&
                read.equalsIgnoreCase("NONE") &&
                update.equalsIgnoreCase("NONE") &&
                delete.equalsIgnoreCase("NONE")) return false;
        return true;
    }

    public boolean hasAnyGrantRight() {
        if (create.equalsIgnoreCase("GRANT") ||
                read.equalsIgnoreCase("GRANT") ||
                update.equalsIgnoreCase("GRANT") ||
                delete.equalsIgnoreCase("GRANT")) return true;
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(create.equals("NONE")?" ":(create.equals("GRANT")?"Id ":"I "));
        stringBuilder.append(read.equals("NONE")?" ":(read.equals("GRANT")?"Sd ":"S "));
        stringBuilder.append(update.equals("NONE")?" ":(update.equals("GRANT")?"Ud ":"U "));
        stringBuilder.append(delete.equals("NONE")?" ":(delete.equals("GRANT")?"Dd ":"D "));
        return stringBuilder.toString();
    }
}
