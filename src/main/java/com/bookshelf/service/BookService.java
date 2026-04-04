package com.bookshelf.service;

import com.bookshelf.entity.Book;
import com.bookshelf.entity.ReadingStatus;
import com.bookshelf.exception.BookNotFoundException;
import com.bookshelf.exception.DuplicateIsbnException;
import com.bookshelf.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book createBook(Book book) {
        if (book.getIsbn() != null) {
            bookRepository.findByIsbn(book.getIsbn())
                    .ifPresent(existing -> {
                        throw new DuplicateIsbnException(book.getIsbn());
                    });
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existing = getBookById(id);
        if (updatedBook.getIsbn() != null
                && !updatedBook.getIsbn().equals(existing.getIsbn())) {
            bookRepository.findById(updatedBook.getId())
                    .ifPresent(other -> {
                        throw new DuplicateFormatFlagsException(updatedBook.getIsbn());
                    });
        }
        existing.setTitle(updatedBook.getTitle());
        existing.setDescription(updatedBook.getDescription());
        existing.setIsbn(updatedBook.getIsbn());
        existing.setPublisher(updatedBook.getPublisher());
        existing.setPageCount(updatedBook.getPageCount());
        existing.setPublishYear(updatedBook.getPublishYear());
        existing.setLanguage(updatedBook.getLanguage());

        return bookRepository.save(existing);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    public Book updateReadingStatus(Long id, ReadingStatus newStatus) {
        Book book = getBookById(id);
        book.setReadingStatus(newStatus);
        return bookRepository.save(book);
    }

    public List<Book> getBooksByStatus(ReadingStatus status) {
        return bookRepository.findByReadingStatus(status);
    }

    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
