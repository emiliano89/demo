package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.dto.WeatherResponseDto;
import com.example.demo.service.PersonService;
import org.hibernate.QueryException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        //given
        List<PersonDto> personDtoList = new ArrayList<>();
        personDtoList.add(Mockito.mock(PersonDto.class));
        when(personService.findAll()).thenReturn(personDtoList);
        ResponseEntity<Object> expected = new ResponseEntity<>(personDtoList,
                null, HttpStatus.OK);

        //when
        ResponseEntity<Object> respose =  controller.findAll();

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void secured() {
        //given
        List<PersonDto> personDtoList = new ArrayList<>();
        personDtoList.add(Mockito.mock(PersonDto.class));
        when(personService.findAll()).thenReturn(personDtoList);
        ResponseEntity<Object> expected = new ResponseEntity<>(personDtoList,
                null, HttpStatus.OK);

        //when
        ResponseEntity<Object> respose =  controller.secured();

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void getById() {
        //given
        PersonDto personDto = Mockito.mock(PersonDto.class);
        when(personService.searchPerson(1L)).thenReturn(personDto);
        ResponseEntity<Object> expected = new ResponseEntity<>(personDto,
                null, HttpStatus.OK);

        //when
        ResponseEntity<Object> respose =  controller.getById(1L);

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void getByIdError() {
        //given
        PersonDto personDto = Mockito.mock(PersonDto.class);
        when(personService.searchPerson(1L)).thenReturn(null);
        ResponseEntity<Object> expected = new ResponseEntity<>(null,
                null, HttpStatus.NOT_FOUND);

        //when
        ResponseEntity<Object> respose =  controller.getById(1L);

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void save() {
        //given
        PersonDto personDto = Mockito.mock(PersonDto.class);
        when(personService.savePerson(personDto)).thenReturn(true);
        ResponseEntity<Object> expected = new ResponseEntity<>(true,
                null, HttpStatus.CREATED);

        //when
        ResponseEntity<Object> respose =  controller.save(personDto);

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void saveError() {
        //given
        PersonDto personDto = Mockito.mock(PersonDto.class);
        when(personService.savePerson(personDto)).thenReturn(false);
        ResponseEntity<Object> expected = new ResponseEntity<>(null,
                null, HttpStatus.BAD_REQUEST);

        //when
        ResponseEntity<Object> respose =  controller.save(personDto);

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void update() {
        //given
        PersonDto personDto = Mockito.mock(PersonDto.class);
        when(personService.savePerson(personDto)).thenReturn(true);
        ResponseEntity<Object> expected = new ResponseEntity<>(true,
                null, HttpStatus.CREATED);

        //when
        ResponseEntity<Object> respose =  controller.update(personDto);

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void updateError() {
        //given
        PersonDto personDto = Mockito.mock(PersonDto.class);
        when(personService.savePerson(personDto)).thenReturn(false);
        ResponseEntity<Object> expected = new ResponseEntity<>(null,
                null, HttpStatus.BAD_REQUEST);

        //when
        ResponseEntity<Object> respose =  controller.update(personDto);

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void delete() {
        //given
        doNothing().when(personService).deletePerson(1L);
        ResponseEntity<Object> expected = new ResponseEntity<>(null,
                null, HttpStatus.OK);

        //when
        ResponseEntity<Object> respose =  controller.delete(1L);

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void deleteError() {
        //given
        QueryException e = new QueryException("");
        doThrow(e).when(personService).deletePerson(1L);
        ResponseEntity<Object> expected = new ResponseEntity<>(e.getMessage(),
                null, HttpStatus.BAD_REQUEST);

        //when
        ResponseEntity<Object> respose =  controller.delete(1L);

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void getWeather() throws Exception {
        //given
        String city = "CORDOBA";
        WeatherResponseDto  weatherResponseDto = Mockito.mock(WeatherResponseDto.class);
        when(personService.getWeather(city)).thenReturn(weatherResponseDto);
        ResponseEntity<Object> expected = new ResponseEntity<>(weatherResponseDto,
                null, HttpStatus.OK);

        //when
        ResponseEntity<Object> respose =  controller.getWeather(city);

        //then
        assertEquals(expected, respose);
    }

    @Test
    public void getWeatherError() throws Exception {
        //given
        String city = "CORDOBA";
        when(personService.getWeather(city)).thenReturn(null);
        ResponseEntity<Object> expected = new ResponseEntity<>("No existe la ciudad. Las ciudades disponibles son CORDOBA, BUENOS AIRES y ROSARIO",
                null, HttpStatus.BAD_REQUEST);

        //when
        ResponseEntity<Object> respose =  controller.getWeather(city);

        //then
        assertEquals(expected, respose);
    }
}