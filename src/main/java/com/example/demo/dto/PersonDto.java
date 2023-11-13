package com.example.demo.dto;

import com.example.demo.entities.OtherDisease;
import com.example.demo.entities.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class PersonDto {

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Person person;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private List<OtherDisease> otherDiseaseList;
}
