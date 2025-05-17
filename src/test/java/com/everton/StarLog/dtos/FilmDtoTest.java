package com.everton.StarLog.dtos;

import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

class FilmDtoTest {
    @Test
    void testPojo() {
        assertPojoMethodsFor(FilmDto.class).testing(Method.GETTER, Method.SETTER)
                .areWellImplemented();
    }
}