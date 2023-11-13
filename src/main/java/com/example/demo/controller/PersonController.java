package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.dto.WeatherResponseDto;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

    @RequestMapping(value = "public", method = RequestMethod.GET)
    public ResponseEntity<Object> findAll() {
        List<PersonDto> personas = personService.findAll();

        return new ResponseEntity<>(personas,
                null, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "secured", method = RequestMethod.GET)
    public ResponseEntity<Object> secured() {
        List<PersonDto> personas = personService.findAll();

        return new ResponseEntity<>(personas,
                null, HttpStatus.OK);
    }

    @RequestMapping(value = "public/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        PersonDto response = personService.searchPerson(id);
        if (response == null) {
            return new ResponseEntity<>(null,
                    null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,
                null, HttpStatus.OK);
    }

    @RequestMapping(value = "public/save", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody PersonDto dto) {
        boolean response = personService.savePerson(dto);
        if (!response) {
            return new ResponseEntity<>(null,
                    null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,
                null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "public/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody PersonDto dto) {
        boolean response = personService.savePerson(dto);
        if (!response) {
            return new ResponseEntity<>(null,
                    null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,
                null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "public/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            personService.deletePerson(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),
                    null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null,
                null, HttpStatus.OK);
    }

    @RequestMapping(value = "public/city/{city}", method = RequestMethod.GET)
    public ResponseEntity<Object> getWeather(@PathVariable String city) throws Exception {
        WeatherResponseDto response = personService.getWeather(city);
        if(response == null) {
            return new ResponseEntity<>("No existe la ciudad. Las ciudades disponibles son CORDOBA, BUENOS AIRES y ROSARIO",
                    null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,
                null, HttpStatus.OK);
    }

}
