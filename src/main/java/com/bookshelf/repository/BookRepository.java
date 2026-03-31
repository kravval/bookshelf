package com.bookshelf.repository;

import com.bookshelf.entity.Book;
import com.bookshelf.entity.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByReadingStatus(ReadingStatus status);

    List<Book> findByPublisher(String publisher);

    List<Book> findByPublishYearGreaterThan(int year);

    List<Book> findByTitleContainingIgnoreCase(String keyword);

    Optional<Book> findByIsbn(String isbn);
}
