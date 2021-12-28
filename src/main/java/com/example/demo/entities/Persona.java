package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonValue;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "PERSONAS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    @JsonValue
    @NotNull
    private Long id;

    @Column(name = "NOMBRE_COMPLETO")
    @NotNull
    private String nombreCompleto;

    @Column(name = "IDENTIFICACION")
    @NotNull
    private Long identificacion;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "ESTADO")
    private boolean estado;

    @Column(name = "MANEJA")
    private boolean maneja;

    @Column(name = "USA_LENTES")
    private boolean usaLentes;

    @Column(name = "DIABETICO")
    private boolean diabetico;

}
