package com.bsk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "Zakupy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "iddostawcy")
    private Vendor vendor;

    @NotBlank
    @Size(min = 5)
    @Pattern(regexp = "^[a-zA-z0-9-]")
    @Column(name = "nrfaktury")
    private String invoiceNumber;

    public Purchase(Date date, Vendor vendor, String invoiceNumber) {
        this.date = date;
        this.vendor = vendor;
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        return getId().toString();
    }
}
