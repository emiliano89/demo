package com.example.demo.service;

import com.example.demo.dto.PersonDto;
import com.example.demo.dto.WeatherResponseDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> findAll();
    PersonDto searchPerson(Long id);
    boolean savePerson(PersonDto dto);
    void deletePerson(Long id);
    WeatherResponseDto getWeather(String city) throws Exception;
}
