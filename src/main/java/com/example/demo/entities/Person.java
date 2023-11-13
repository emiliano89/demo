package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "PERSONAS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Person implements Serializable {

    @Id
    @Column(name = "ID")
    @NotNull
    private Long id;

    @Column(name = "NOMBRE_COMPLETO")
    @NotNull
    private String fullName;

    @Column(name = "IDENTIFICACION")
    @NotNull
    private Long identification;

    @Column(name = "EDAD")
    private Integer age;

    @Column(name = "GENERO")
    private String gender;

    @Column(name = "ESTADO")
    private Boolean state;

    @Column(name = "MANEJA")
    private Boolean drive;

    @Column(name = "USA_LENTES")
    private Boolean useGlasses;

    @Column(name = "DIABETICO")
    private Boolean diabetic;

}
