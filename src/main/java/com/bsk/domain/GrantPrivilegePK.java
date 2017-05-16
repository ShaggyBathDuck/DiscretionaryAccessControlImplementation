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


}
