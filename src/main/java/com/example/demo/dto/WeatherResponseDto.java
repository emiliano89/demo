package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherResponseDto {

    @JsonProperty("City")
    private String city;
    @JsonProperty("Temperature")
    private String temperature;
}
