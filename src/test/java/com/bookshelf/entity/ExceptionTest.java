package com.bookshelf.entity;

import com.bookshelf.exception.BookNotFoundException;
import com.bookshelf.exception.DuplicateIsbnException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Custom Exceptions")
public class ExceptionTest {

    @Test
    @DisplayName("BookNotFoundException содержит id в сообщении")
    void bookNotFoundShouldContainId() {
        BookNotFoundException exception = new BookNotFoundException(42L);
        assertEquals("Книга с id 42 не найдена", exception.getMessage());
    }

    @Test
    @DisplayName("BookNotFoundException наследуется от RuntimeException")
    void bookNotFoundShouldBeRuntimeException() {
        BookNotFoundException exception = new BookNotFoundException(1L);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("BookNotFoundException можно бросить и поймать")
    void bookNotFoundShouldBeThrowable() {
        assertThrows(BookNotFoundException.class,
                () -> { throw new BookNotFoundException(99L); });
    }

    @Test
    @DisplayName("DuplicateIsbnException содержит ISBN в сообщении")
    void duplicateIsbnShouldContainIsbn() {
        DuplicateIsbnException exception =
                new DuplicateIsbnException("978-5-6041394-3-5");
        assertEquals("Книга с ISBN 978-5-6041394-3-5 уже существует",
                exception.getMessage());
    }

    @Test
    @DisplayName("DuplicateIsbnException наследуется от RuntimeException")
    void duplicateIsbnShouldBeRuntimeException() {
        DuplicateIsbnException exception =
                new DuplicateIsbnException("978-5-123");
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    @DisplayName("DuplicateIsbnException можно бросить и поймать")
    void duplicateIsbnShouldBeThrowable() {
        assertThrows(DuplicateIsbnException.class,
                () -> { throw new DuplicateIsbnException("978-5-123"); });
    }
}
