package com.example.demo.dto;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class CurrentDtoTest {

    private CurrentDto currentDto;

    @Test
    public void dtoTest() {
        currentDto = new CurrentDto();
        currentDto.setInterval(1L);
        currentDto.setTime("TEST");
        currentDto.setTemperature2m(12.5);

        assertEquals(java.util.Optional.of(1L), java.util.Optional.ofNullable(currentDto.getInterval()));
        assertEquals("TEST", currentDto.getTime());
        assertEquals(java.util.Optional.of(12.5), java.util.Optional.ofNullable(currentDto.getTemperature2m()));
    }

}