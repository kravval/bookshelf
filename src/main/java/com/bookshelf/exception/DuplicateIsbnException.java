package com.bookshelf.exception;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String isbn) {
        super("Книга с ISBM " + isbn + " уже существует");
    }
}
