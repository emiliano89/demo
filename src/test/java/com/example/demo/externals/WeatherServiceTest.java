package com.example.demo.externals;

import com.example.demo.dto.CurrentDto;
import com.example.demo.dto.WeatherResponseDto;
import com.example.demo.dto.WeatherServiceResponseDto;
import com.example.demo.entities.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void weatherResponse() {
        //given
        City city = Mockito.mock(City.class);
        WeatherServiceResponseDto weatherServiceResponseDto = Mockito.mock(WeatherServiceResponseDto.class);
        CurrentDto currentDto = Mockito.mock(CurrentDto.class);
        Mockito.when(weatherServiceResponseDto.getCurrent()).thenReturn(currentDto);
        Mockito.when(currentDto.getTemperature2m()).thenReturn(14.9);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(weatherServiceResponseDto);
        Mockito.when(city.getCityName()).thenReturn("CORDOBA");

        //when
        WeatherResponseDto result = service.weatherResponse(city);

        //then
        assertEquals(city.getCityName(), result.getCity());
        assertEquals(weatherServiceResponseDto.getCurrent().getTemperature2m().toString(), result.getTemperature());
    }

    @Test
    public void weatherResponseError() {
        //given
        City city = Mockito.mock(City.class);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenThrow(new RestClientException(""));

        //when
        WeatherResponseDto result = service.weatherResponse(city);

        //then
        assertNull(result);
    }
}