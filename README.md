# Book and Patron Management System

## Overview
This application is designed to manage books and patrons in a library setting. It supports various CRUD operations related to book management, patron management, and record-keeping for borrowing and returning books. The project is built using Spring Boot, employing RESTful APIs for all interactions.

## Technology Stack
- **Spring Boot**: Framework for building Java-based enterprise applications.
- **PostgreSQL**: Database for storing all application data.
- **JUnit & Mockito**: For unit testing.
- **Spring Data JPA**: To interact with the database using Java Persistence API.
- **Spring Security**: For authentication and authorization.

## Getting Started

### Prerequisites
- JDK 11 or later
- Maven 3.6 or later
- PostgreSQL 12 or later

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/book-patron-management.git
   cd book-patron-management

2. **Set up the database:**
   - Execute the SQL scripts located in `src/main/resources/db/migration` to create the tables and initialize the database.

3. **Configure the application:**
   - Edit `src/main/resources/application.properties` to set your database credentials and other configurations as needed.

4. **Build the application:**
   ```bash
   mvn clean package

### Project EndPoints


| Method | Endpoint                          | Description                                    |
|--------|-----------------------------------|------------------------------------------------|
| GET    | `/api/books`                      | Retrieves a list of all books.                 |
| GET    | `/api/books/{id}`                 | Retrieves details of a specific book by ID.    |
| POST   | `/api/books`                      | Adds a new book to the library.                |
| PUT    | `/api/books/{id}`                 | Updates an existing book's information.        |
| DELETE | `/api/books/{id}`                 | Removes a book from the library.               |
| GET    | `/api/patrons`                    | Retrieves a list of all patrons.               |
| GET    | `/api/patrons/{id}`               | Retrieves details of a specific patron by ID.  |
| POST   | `/api/patrons`                    | Adds a new patron to the system.               |
| PUT    | `/api/patrons/{id}`               | Updates an existing patron's information.      |
| DELETE | `/api/patrons/{id}`               | Removes a patron from the system.              |
| POST   | `/api/borrow/{bookId}/patron/{patronId}` | Allows a patron to borrow a book.         |
| PUT    | `/api/return/{bookId}/patron/{patronId}` | Records the return of a borrowed book.   |
| POST   | `/api/auth/token`                 | Authorizes the user and provides a JWT token.  |


### Customization
- **Replace URLs and user names**: Ensure you replace the repository URL and any specific user or database names with the actual ones used in your project.
- **Detailed API Endpoint Descriptions**: You may need to expand upon the API endpoint descriptions based on your application's functionality.
- **Authentication Details**: Fill in detailed authentication instructions depending on what method you've implemented (e.g., OAuth, Basic Auth, API keys).
- **Error Handling**: Provide examples of common error responses.

This README is comprehensive and should meet the needs of both developers looking to understand or contribute to the project and users looking to use the API.

