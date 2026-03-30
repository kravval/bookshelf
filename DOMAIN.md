# BookShelf — Domain Model

## Entities


+--------------------+       +--------------------+
|       Book         |       |      Author        |
+--------------------+       +--------------------+
| id                 |       | id                 |
| title              |       | firstName          |
| description        |       | lastName           |
| isbn               |       | bio                |
| publisher          |       +--------+-----------+
| pageCount          |                |
| publishYear        |    ManyToMany  |
| language           |<---------------+
| readingStatus      |
| pdfFilePath        |       +--------------------+
| createdAt          |       |       Tag          |
| updatedAt          |       +--------------------+
|                    |       | id                 |
|                    |       | name               |
|                    |<------+--------------------+
+--------------------+           ManyToMany


## Relationships

- Book <-> Author: ManyToMany (a book can have multiple authors, an author can write multiple books)
- Book <-> Tag: ManyToMany (a book can have multiple tags, a tag can belong to multiple books)

## Reading Status (Enum)

- NOT_READ — default when book is added
- IN_PROGRESS — currently reading
- COMPLETED — finished and reviewed
