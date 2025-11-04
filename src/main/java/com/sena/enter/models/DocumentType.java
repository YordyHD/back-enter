package com.sena.enter.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "document_type")
public class DocumentType implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "initials", length = 10, nullable = false, unique = true)
    private String initi;

    @NotNull
    @Size(max = 100)
    @Column(name = "document_name", length = 100, nullable = false, unique = true)
    private String documentN;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentType")
    @JsonIgnoreProperties(value = { "user", "bills", "views", "filmGenres", "documentType", "sex", "cities" }, allowSetters = true)
    private Set<Customer> customers = new HashSet<>();
}
