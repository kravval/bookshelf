package com.bookshelf.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ReadingStatus Enum")
class ReadingStatusTest {

    @Test
    @DisplayName("Enum содержит ровно 3 значения")
    void shouldHaveThreeValues() {
        ReadingStatus[] values = ReadingStatus.values();
        assertEquals(3, values.length);
    }

    @Test
    @DisplayName("NOT_READ существует")
    void shouldHaveNotReadValue() {
        ReadingStatus status = ReadingStatus.valueOf("NOT_READ");
        assertEquals(ReadingStatus.NOT_READ, status);
    }

    @Test
    @DisplayName("IN_PROGRESS существует")
    void shouldHaveInProgressValue() {
        ReadingStatus status = ReadingStatus.valueOf("IN_PROGRESS");
        assertEquals(ReadingStatus.IN_PROGRESS, status);
    }

    @Test
    @DisplayName("COMPLETED существует")
    void shouldHaveCompletedValue() {
        ReadingStatus status = ReadingStatus.valueOf("COMPLETED");
        assertEquals(ReadingStatus.COMPLETED, status);
    }

    @Test
    @DisplayName("Несуществующее значение бросает IllegalArgumentException")
    void shouldThrowExceptionForInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> ReadingStatus.valueOf("INVALID"));
    }
}