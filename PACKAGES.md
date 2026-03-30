# BookShelf — Package Structure


com.bookshelf
|
|-- BookshelfApplication.java        <-- @SpringBootApplication (entry point)
|
|-- controller/
|   |-- BookWebController.java       <-- @Controller (HTML pages)
|   +-- BookRestController.java      <-- @RestController (JSON API)
|
|-- service/
|   +-- BookService.java             <-- @Service (business logic)
|
|-- repository/
|   +-- BookRepository.java          <-- @Repository (data access)
|
|-- entity/
|   |-- Book.java                    <-- @Entity (JPA, maps to DB table)
|   |-- Author.java                  <-- @Entity
|   |-- Tag.java                     <-- @Entity
|   +-- ReadingStatus.java           <-- Enum (NOT_READ, IN_PROGRESS, COMPLETED)
|
|-- dto/
|   |-- BookCreateRequest.java       <-- what client sends to create a book
|   |-- BookUpdateRequest.java       <-- what client sends to update a book
|   |-- BookResponse.java            <-- full book data returned to client
|   +-- BookShortResponse.java       <-- brief book data for lists
|
+-- exception/
    |-- BookNotFoundException.java   <-- 404
    +-- DuplicateIsbnException.java  <-- 409

## What is a bean and what is not

**Beans** (managed by Spring container): Controllers, Services, Repositories — created once at startup, injected via constructors.

**Not beans** (plain Java objects): Entities, DTOs, Enums, Exceptions — created by application code with `new`, Spring does not manage them.
