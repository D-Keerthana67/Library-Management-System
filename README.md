# Library Management System with Smart Fine & Recommendation Engine

A Java 17 console application to manage books and members, borrow and return books, calculate overdue fines, and recommend books based on previous borrowing categories.

## Features
- Add books and register members.
- View and search books by title, author, or category.
- Borrow available books with an automatic 14-day due date.
- Return books and calculate late fines automatically.
- Fine rate: Rs. 5 per overdue day.
- Recommend available books in categories the member has borrowed before.
- Validate IDs, book availability, and repeated returns.
- Demonstrates OOP, ArrayList, HashMap, LocalDate, Streams, and custom exceptions.

## Technology
Java 17, Maven, Java Collections, Java Streams, and the Java Time API.

## Run
Install JDK 17 and Maven. From the project directory run:

    mvn clean compile
    mvn exec:java

## Fine example
The borrowing period is 14 days. If a book is returned 3 days after its due date, the fine is 3 x Rs. 5 = Rs. 15. Returning on or before the due date has no fine.

## Recommendation logic
The system records the categories of books a member borrows. It recommends available books in those categories and filters out titles that member has already borrowed. Members with no borrowing history receive a message explaining how recommendations are generated.

## Sample IDs
Books: B101, B102, B103, B104, B105. Members: M101, M102.

## Project structure
- model: Book, Member, Transaction
- service: LibraryService, RecommendationEngine
- exception: LibraryException, BookNotAvailableException
- Main.java: console menu and user interaction

## Note
This learning project stores data in memory, so records reset when the program exits. JDBC/MySQL persistence and a graphical interface are possible future improvements.
