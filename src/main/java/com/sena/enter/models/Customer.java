package com.sena.enter.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "customer")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "document_number", length = 50, nullable = false)
    private String documentNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "second_name", length = 50)
    private String secondName;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_las_name", length = 50, nullable = false)
    private String firstLasName;

    @Size(max = 50)
    @Column(name = "second_last_name", length = 50)
    private String secondLastName;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonIgnoreProperties(value = { "billMembreships", "customer" }, allowSetters = true)
    private Set<Bill> bills = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonIgnoreProperties(value = { "customer", "audiovisualContent" }, allowSetters = true)
    private Set<View> views = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    @JoinTable(
        name = "rel_customer__film_genre",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "film_genre_id")
    )
    @JsonIgnoreProperties(value = { "audiovisualContents", "customers" }, allowSetters = true)
    private Set<FilmGenre> filmGenres = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "customers" }, allowSetters = true)
    private DocumentType documentType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "customers" }, allowSetters = true)
    private Sex sex;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "customers", "departament" }, allowSetters = true)
    private City cities;
}
