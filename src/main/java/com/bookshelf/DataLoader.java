package com.bookshelf;

import com.bookshelf.entity.Book;
import com.bookshelf.entity.ReadingStatus;
import com.bookshelf.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;

    // Dependency Injection — Spring подставит BookRepository
    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        // Создаём книги через Java-объекты
        Book book1 = new Book();
        book1.setTitle("Spring в действии");
        book1.setPublisher("Manning");
        book1.setPublishYear(2022);
        book1.setPageCount(520);
        book1.setLanguage("Русский");
        book1.setDescription("Полное руководство по Spring Framework и Spring Boot.");

        Book book2 = new Book();
        book2.setTitle("Эффективная Java");
        book2.setDescription("Лучшие практики программирования на Java от Джошуа Блоха.");
        book2.setIsbn("978-5-6041394-3-5");
        book2.setPublisher("Диалектика");
        book2.setPageCount(464);
        book2.setPublishYear(2019);
        book2.setLanguage("Русский");
        book2.setReadingStatus(ReadingStatus.IN_PROGRESS);

        Book book3 = new Book();
        book3.setTitle("Чистый код");
        book3.setDescription("Создание, анализ и рефакторинг.");
        book3.setIsbn("978-5-4461-0960-9");
        book3.setPublisher("Питер");
        book3.setPageCount(464);
        book3.setPublishYear(2013);
        book3.setLanguage("Русский");
        book3.setReadingStatus(ReadingStatus.COMPLETED);

        Book book4 = new Book();
        book4.setTitle("Паттерны проектирования");
        book4.setDescription("Банда четырёх. Классика объектно-ориентированного дизайна.");
        book4.setIsbn("978-5-4461-1210-4");
        book4.setPublisher("Питер");
        book4.setPageCount(448);
        book4.setPublishYear(2020);
        book4.setLanguage("Русский");

        Book book5 = new Book();
        book5.setTitle("Java. Библиотека профессионала. Том 1");
        book5.setDescription("Основы. Кей Хорстманн. Полное руководство по Java SE.");
        book5.setIsbn("978-5-907114-79-1");
        book5.setPublisher("Диалектика");
        book5.setPageCount(864);
        book5.setPublishYear(2020);
        book5.setLanguage("Русский");

        // Сохраняем в базу данных
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);

        // Выводим в консоль для проверки
        System.out.println("=== Загружено книг: " + bookRepository.count() + " ===");

        // Проверяем query-метод
        System.out.println("\n--- Книги издательства 'Питер' ---");
        bookRepository.findByPublisher("Питер")
                .forEach(b -> System.out.println("  " + b.getTitle()));

        // Проверяем поиск по названию
        System.out.println("\n--- Поиск по 'java' ---");
        bookRepository.findByTitleContainingIgnoreCase("java")
                .forEach(b -> System.out.println("  " + b.getTitle()));

        // Проверяем фильтр по статусу
        System.out.println("\n--- Книги со статусом COMPLETED ---");
        bookRepository.findByReadingStatus(ReadingStatus.COMPLETED)
                .forEach(b -> System.out.println("  " + b.getTitle()));
    }
}
