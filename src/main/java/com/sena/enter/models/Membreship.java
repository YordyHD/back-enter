package com.sena.enter.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "membreship")
public class Membreship implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "membership_name", length = 255, nullable = false)
    private String membershipN;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal pri;

    @Column(name = "duration")
    private Integer durat;

    @Lob
    @Column(name = "imagen")
    private byte[] ima;

    @Column(name = "imagen_content_type")
    private String imaContentType;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String descrip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "membreship")
    @JsonIgnoreProperties(value = { "bill", "membreship" }, allowSetters = true)
    private Set<BillMembreship> billMembreships = new HashSet<>();
}
