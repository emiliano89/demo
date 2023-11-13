package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class CurrentDto {
    @JsonProperty("time")
    private String time;

    @JsonProperty("interval")
    private Long interval;

    @JsonProperty("temperature_2m")
    private Double temperature2m;
}
