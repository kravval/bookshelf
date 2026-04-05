package com.bookshelf.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Book Entity")
class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @Test
    @DisplayName("Новая книга имеет статус NOT_READ по умолчанию")
    void shouldHaveNotReadStatusByDefault() {
        assertEquals(ReadingStatus.NOT_READ, book.getReadingStatus());
    }

    @Test
    @DisplayName("id новой книги равен null")
    void shouldHaveNullIdBeforeSaving() {
        assertNull(book.getId());
    }


    @Test
    @DisplayName("Описание новой книги равно null")
    void shouldHaveNullDescriptionByDefault() {
        assertNull(book.getDescription());
    }

    @Test
    @DisplayName("ISBN новой книги равен null")
    void shouldHaveNullIsbnByDefault() {
        assertNull(book.getIsbn());
    }

    @Test
    @DisplayName("Количество страниц новой книги равно null")
    void shouldHaveNullPageCountByDefault() {
        assertNull(book.getPageCount());
    }

    @Test
    @DisplayName("setTitle / getTitle работают корректно")
    void shouldSetAndGetTitle() {
        book.setTitle("Эффективная Java");
        assertEquals("Эффективная Java", book.getTitle());
    }

    @Test
    @DisplayName("setDescription / getDescription работают корректно")
    void shouldSetAndGetDescription() {
        book.setDescription("Лучшие практики программирования на Java");
        assertEquals("Лучшие практики программирования на Java",
                book.getDescription());
    }

    @Test
    @DisplayName("setIsbn / getIsbn работают корректно")
    void shouldSetAndGetIsbn() {
        book.setIsbn("978-5-6041394-3-5");
        assertEquals("978-5-6041394-3-5", book.getIsbn());
    }

    @Test
    @DisplayName("setPublisher / getPublisher работают корректно")
    void shouldSetAndGetPublisher() {
        book.setPublisher("Питер");
        assertEquals("Питер", book.getPublisher());
    }

    @Test
    @DisplayName("setPageCount / getPageCount работают корректно")
    void shouldSetAndGetPageCount() {
        book.setPageCount(464);
        assertEquals(464, book.getPageCount());
    }

    @Test
    @DisplayName("setPublishYear / getPublishYear работают корректно")
    void shouldSetAndGetPublishYear() {
        book.setPublishYear(2019);
        assertEquals(2019, book.getPublishYear());
    }

    @Test
    @DisplayName("setLanguage / getLanguage работают корректно")
    void shouldSetAndGetLanguage() {
        book.setLanguage("Русский");
        assertEquals("Русский", book.getLanguage());
    }

    @Test
    @DisplayName("Можно изменить статус на IN_PROGRESS")
    void shouldChangeStatusToInProgress() {
        book.setReadingStatus(ReadingStatus.IN_PROGRESS);
        assertEquals(ReadingStatus.IN_PROGRESS, book.getReadingStatus());
    }

    @Test
    @DisplayName("Можно изменить статус на COMPLETED")
    void shouldChangeStatusToCompleted() {
        book.setReadingStatus(ReadingStatus.COMPLETED);
        assertEquals(ReadingStatus.COMPLETED, book.getReadingStatus());
    }
}