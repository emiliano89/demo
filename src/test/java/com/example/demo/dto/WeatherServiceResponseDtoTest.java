package com.example.demo.dto;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class WeatherServiceResponseDtoTest {

    @InjectMocks
    private WeatherServiceResponseDto weatherServiceResponseDto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dtoTest() {
        weatherServiceResponseDto = new WeatherServiceResponseDto();
        CurrentDto currentDto = Mockito.mock(CurrentDto.class);
        CurrentUnitsDto currentUnitsDto = Mockito.mock(CurrentUnitsDto.class);
        weatherServiceResponseDto.setCurrent(currentDto);
        weatherServiceResponseDto.setElevation(12.5);
        weatherServiceResponseDto.setLatitude(133.5);
        weatherServiceResponseDto.setLongitude(5442.2);
        weatherServiceResponseDto.setCurrentUnits(currentUnitsDto);
        weatherServiceResponseDto.setGenerationtimeMs(21.32);
        weatherServiceResponseDto.setTimezone("TEST");
        weatherServiceResponseDto.setTimezone_abbreviation("TEST");
        weatherServiceResponseDto.setUtcOffsetSeconds(22.2);


        assertEquals(currentDto, weatherServiceResponseDto.getCurrent());
        assertEquals(Double.valueOf(12.5), weatherServiceResponseDto.getElevation());
        assertEquals(Double.valueOf(133.5), weatherServiceResponseDto.getLatitude());
        assertEquals(Double.valueOf(5442.2), weatherServiceResponseDto.getLongitude());
        assertEquals(currentUnitsDto, weatherServiceResponseDto.getCurrentUnits());
        assertEquals(Double.valueOf(21.32), weatherServiceResponseDto.getGenerationtimeMs());
        assertEquals("TEST", weatherServiceResponseDto.getTimezone());
        assertEquals("TEST", weatherServiceResponseDto.getTimezone_abbreviation());
        assertEquals(Double.valueOf(22.2), weatherServiceResponseDto.getUtcOffsetSeconds());
    }

}