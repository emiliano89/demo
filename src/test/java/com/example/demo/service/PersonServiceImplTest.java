package com.example.demo.service;

import com.example.demo.dao.CityDao;
import com.example.demo.dao.OtherDiseaseDao;
import com.example.demo.dao.PersonDao;
import com.example.demo.dto.PersonDto;
import com.example.demo.dto.WeatherResponseDto;
import com.example.demo.entities.City;
import com.example.demo.entities.OtherDisease;
import com.example.demo.entities.Person;
import com.example.demo.externals.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personServiceImpl;

    @Mock
    private OtherDiseaseDao otherDiseaseDao;

    @Mock
    private PersonDao personDao;

    @Mock
    private WeatherService weatherService;

    @Mock
    private CityDao cityDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        //given
        List<Person> personList = new ArrayList<>();
        List<OtherDisease> otherDiseaseList = new ArrayList<>();
        OtherDisease otherDisease = Mockito.mock(OtherDisease.class);
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1L);
        personList.add(person);
        otherDiseaseList.add(otherDisease);
        Mockito.when(personDao.findAll()).thenReturn(personList);
        Mockito.when(otherDiseaseDao.findByPersonId(Mockito.anyLong())).thenReturn(otherDiseaseList);

        //when
        List<PersonDto> personaDtoList = personServiceImpl.findAll();

        //then
        assertFalse(personaDtoList.isEmpty());
        assertEquals(1, personaDtoList.size());
        assertEquals(person, personaDtoList.get(0).getPerson());
        assertEquals(otherDiseaseList, personaDtoList.get(0).getOtherDiseaseList());
    }

    @Test
    public void searchPerson() {
        //given
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1L);
        List<OtherDisease> otherDiseaseList = new ArrayList<>();
        OtherDisease otherDisease = Mockito.mock(OtherDisease.class);
        otherDiseaseList.add(otherDisease);
        Mockito.when(personDao.findById(1L)).thenReturn(Optional.of(person));
        Mockito.when(otherDiseaseDao.findByPersonId(1L)).thenReturn(otherDiseaseList);

        //when
        PersonDto response = personServiceImpl.searchPerson(1L);

        //then
        assertEquals(Long.valueOf(1), response.getPerson().getId());
        assertEquals(person, response.getPerson());
        assertEquals(otherDiseaseList.get(0), response.getOtherDiseaseList().get(0));
        assertEquals(otherDiseaseList.size(), response.getOtherDiseaseList().size());
    }

    @Test
    public void searchPersonError() {
        //given
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1L);
        Mockito.when(personDao.findById(1L)).thenReturn(Optional.empty());

        //when
        PersonDto response = personServiceImpl.searchPerson(1L);

        //then
        assertNull(response);
    }

    @Test
    public void savePerson() {
        PersonDto dto = new PersonDto();
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1L);
        List<OtherDisease> otherDiseaseList = new ArrayList<>();
        OtherDisease otherDisease = Mockito.mock(OtherDisease.class);
        otherDiseaseList.add(otherDisease);
        dto.setPerson(person);
        dto.setOtherDiseaseList(otherDiseaseList);
        Mockito.when(personDao.save(Mockito.any())).thenReturn(person);
        Mockito.when(otherDiseaseDao.saveAll(otherDiseaseList)).thenReturn(otherDiseaseList);

        boolean result = personServiceImpl.savePerson(dto);

        assertTrue(result);
    }

    @Test
    public void savePersonError() {
        PersonDto dto = new PersonDto();
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1L);
        List<OtherDisease> otherDiseaseList = new ArrayList<>();
        OtherDisease otherDisease = Mockito.mock(OtherDisease.class);
        otherDiseaseList.add(otherDisease);
        dto.setPerson(person);
        dto.setOtherDiseaseList(otherDiseaseList);
        Mockito.when(personDao.save(Mockito.any())).thenReturn(Optional.empty());

        boolean result = personServiceImpl.savePerson(dto);

        assertFalse(result);
    }

    @Test
    public void deletePerson() {
        //given
        Long id = 1L;
        doNothing().when(personDao).deleteById(id);
        doNothing().when(otherDiseaseDao).deleteByPersonId(id);
        Exception error = null;

        //when
        try{
            personServiceImpl.deletePerson(id);
        }catch (Exception e) {
            error = e;
        }
        //then
        assertNull(error);
    }

    @Test
    public void getWeather() {
        //given
        City city = Mockito.mock(City.class);
        String c = "CORDOBA";
        WeatherResponseDto expected = new WeatherResponseDto();
        expected.setCity(c);
        expected.setTemperature("25");
        when(cityDao.findByCityName(c)).thenReturn(city);
        when(weatherService.weatherResponse(city)).thenReturn(expected);

        //when
        WeatherResponseDto response  = personServiceImpl.getWeather(c);

        //then
        assertEquals(expected, response);
    }
}