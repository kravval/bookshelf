# BookShelf — Architecture

## Layered Architecture

BookShelf follows a classic three-layer architecture. Each layer has a single responsibility and communicates only with the layer directly below it.

HTTP Request
     |
     v
+-------------------------------------------------+
|  Presentation Layer                             |
|                                                 |
|  BookWebController    (@Controller)             |
|    -> receives HTTP, returns HTML pages         |
|                                                 |
|  BookRestController   (@RestController)         |
|    -> receives HTTP, returns JSON               |
+-------------------------------------------------+
|  Service Layer                                  |
|                                                 |
|  BookService          (@Service)                |
|    -> business logic, validation, rules         |
|                                                 |
|  AuthorService        (@Service)                |
|    -> author-related logic                      |
+-------------------------------------------------+
|  Data Access Layer                              |
|                                                 |
|  BookRepository       (@Repository)             |
|    -> CRUD operations, queries                  |
|                                                 |
|  AuthorRepository     (@Repository)             |
|    -> author data access                        |
+-------------------------------------------------+
|  Database                                       |
|                                                 |
|  PostgreSQL                                     |
|    -> books, authors, tags, book_authors        |
+-------------------------------------------------+


## Key Rules

- Controllers never access repositories directly
- Services contain all business logic
- Repositories only handle data access
- Entities (Book, Author, Tag) are plain Java objects, not beans
- DTOs are used between Presentation and Service layers

## Dependency Injection Flow

Spring container creates all beans at startup:

1. BookRepository (depends on DataSource)
2. BookService (depends on BookRepository)
3. BookWebController (depends on BookService)
4. BookRestController (depends on BookService)

All dependencies are injected via constructors.
