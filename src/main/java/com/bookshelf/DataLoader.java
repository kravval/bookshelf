package com.bookshelf;

import com.bookshelf.entity.Book;
import com.bookshelf.entity.ReadingStatus;
import com.bookshelf.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookService bookService;  // теперь зависит от сервиса

    public DataLoader(BookService bookService) {
        this.bookService = bookService;
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

        Book book3 = new Book();
        book3.setTitle("Чистый код");
        book3.setDescription("Создание, анализ и рефакторинг.");
        book3.setIsbn("978-5-4461-0960-9");
        book3.setPublisher("Питер");
        book3.setPageCount(464);
        book3.setPublishYear(2013);
        book3.setLanguage("Русский");

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

        // Сохраняем через сервис — бизнес-логика применяется
        bookService.createBook(book1);
        bookService.createBook(book2);
        bookService.createBook(book3);
        bookService.createBook(book4);
        bookService.createBook(book5);

        // --- Проверяем методы сервиса ---

        System.out.println("=== Загружено книг: " + bookService.getAllBooks().size() + " ===");

        System.out.println("\n--- Книги издательства 'Питер' ---");
        bookService.searchBooks("код")
                .forEach(b -> System.out.println("  " + b.getTitle()));

        System.out.println("\n--- Поиск по 'java' ---");
        bookService.searchBooks("java")
                .forEach(b -> System.out.println("  " + b.getTitle()));

        System.out.println("\n--- Смена статуса ---");
        Book updated = bookService.updateReadingStatus(2L, ReadingStatus.IN_PROGRESS);
        System.out.println("  " + updated.getTitle() + " → " + updated.getReadingStatus());

        updated = bookService.updateReadingStatus(3L, ReadingStatus.COMPLETED);
        System.out.println("  " + updated.getTitle() + " → " + updated.getReadingStatus());

        System.out.println("\n--- Книги со статусом NOT_READ ---");
        bookService.getBooksByStatus(ReadingStatus.NOT_READ)
                .forEach(b -> System.out.println("  " + b.getTitle()));

        // --- Проверка бизнес-правила: дубликат ISBN ---
        System.out.println("\n--- Проверка: дубликат ISBN ---");
        try {
            Book duplicate = new Book();
            duplicate.setTitle("Дубликат");
            duplicate.setIsbn("978-5-6041394-3-5");  // такой ISBN уже есть у книги 2
            bookService.createBook(duplicate);
            System.out.println("  ОШИБКА: дубликат не обнаружен!");
        } catch (Exception e) {
            System.out.println("  OK: " + e.getMessage());
        }

        // --- Проверка бизнес-правила: книга не найдена ---
        System.out.println("\n--- Проверка: книга не найдена ---");
        try {
            bookService.getBookById(999L);
            System.out.println("  ОШИБКА: исключение не брошено!");
        } catch (Exception e) {
            System.out.println("  OK: " + e.getMessage());
        }
    }
}
