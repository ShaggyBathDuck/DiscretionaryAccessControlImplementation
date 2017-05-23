package com.bsk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class GrantPrivilegePK implements Serializable {
    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "dawca")
    private User giver;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "biorca")
    private User receiver;

    public GrantPrivilegePK() {
    }

    public GrantPrivilegePK(User giver, User receiver) {
        this.giver = giver;
        this.receiver = receiver;
    }

    public User getGiver() {
        return giver;
    }

    public void setGiver(User giver) {
        this.giver = giver;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
