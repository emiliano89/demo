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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonService {

    private final OtherDiseaseDao otherDiseaseDao;

    private final PersonDao personDao;

    private final WeatherService weatherService;

    private final CityDao cityDao;

    private final static Set<String> CITIES = new HashSet<>(Arrays.asList("CORDOBA", "BUENOS AIRES", "ROSARIO"));

    private final Lock lock = new ReentrantLock();

    public List<PersonDto> findAll() {

        List<PersonDto> personaDtoList = new ArrayList<>();
        List<Person> personas = personDao.findAll();

        for (Person person : personas) {
            PersonDto personaDto = new PersonDto();
            personaDto.setPerson(person);
            List<OtherDisease> otraEnfermedadList = otherDiseaseDao.findByPersonId(person.getId());
            personaDto.setOtherDiseaseList(otraEnfermedadList);
            personaDtoList.add(personaDto);
        }
        return personaDtoList;
    }

    @Cacheable("resultadosCache")
    public PersonDto searchPerson(Long id) {
        PersonDto result = new PersonDto();
        List<OtherDisease> otraEnfermedadList = new ArrayList<>();
        try {
            Optional<Person> persona = personDao.findById(id);
            if (persona.get().getId() != null) {
                otraEnfermedadList = otherDiseaseDao.findByPersonId(persona.get().getId());
            }
            result.setPerson(persona.get());
            result.setOtherDiseaseList(otraEnfermedadList);
        } catch (Exception e) {
            log.error("Error al obtener persona por id. {}", e.getMessage());
            result = null;
        }

        return result;
    }

    @Transactional
    public boolean savePerson(PersonDto dto) {
        try {
            personDao.save(dto.getPerson());
            if (!dto.getOtherDiseaseList().isEmpty()) {
                otherDiseaseDao.saveAll(dto.getOtherDiseaseList());
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Transactional
    public void deletePerson(Long id) {
        if (id != null) {
            personDao.deleteById(id);
            otherDiseaseDao.deleteByPersonId(id);
        }
    }

    @Async
    public WeatherResponseDto getWeather(String cityName) {
        lock.lock();
        WeatherResponseDto responseDto = null;
        try {
            if (CITIES.contains(cityName.toUpperCase())) {
                City city = cityDao.findByCityName(cityName.toUpperCase());
                responseDto = weatherService.weatherResponse(city);
            }
        } finally {
            lock.unlock();
        }
        return responseDto;

    }
}
