package com.example.demo.dto;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class CurrentUnitsDtoTest {


    private CurrentUnitsDto currentUnitsDto;

    @Test
    public void dtoTest() {
        currentUnitsDto = new CurrentUnitsDto();
        currentUnitsDto.setInterval("TEST");
        currentUnitsDto.setTime("TEST");
        currentUnitsDto.setTemperature2m("TEST");

        assertEquals("TEST",currentUnitsDto.getInterval());
        assertEquals("TEST", currentUnitsDto.getTime());
        assertEquals("TEST", currentUnitsDto.getTemperature2m());
    }


}