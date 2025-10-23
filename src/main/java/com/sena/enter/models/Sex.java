package com.sena.enter.models;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "sex")
public class Sex implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sex_name", length = 40)
    private String sexN;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sex")
    @JsonIgnoreProperties(value = { "user", "bills", "views", "filmGenres", "documentType", "sex", "cities" }, allowSetters = true)
    private Set<Customer> customers = new HashSet<>();
}
