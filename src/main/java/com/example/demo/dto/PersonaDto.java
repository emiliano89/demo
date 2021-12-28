package com.example.demo.dto;

import com.example.demo.entities.OtraEnfermedad;
import com.example.demo.entities.Persona;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PersonaDto {

    @NotNull
    private Persona persona;

    private List<OtraEnfermedad> otraEnfermedadList;
}
