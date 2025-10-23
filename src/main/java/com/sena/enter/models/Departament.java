package com.sena.enter.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "departament")
public class Departament implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 27)
    @Column(name = "departament_name", length = 27, unique = true)
    private String departamentName;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departament")
    @JsonIgnoreProperties(value = { "customers", "departament" }, allowSetters = true)
    private Set<City> cities = new HashSet<>();
}
