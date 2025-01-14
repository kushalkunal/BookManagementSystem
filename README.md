 
 # Book Management System API

This is a simple REST API for managing books. The application allows users to perform CRUD (Create, Read, Update, Delete) operations on books and provides endpoints for managing books in a library system.

## Features

- Add a new book
- Retrieve information about a book or list of books
- Update book details
- Delete a book from the system
- Sort the list of books on title or author
- Paging is also enabled 

## Api End Points


GET /books - Retrieve a list of all books

GET /books/:id - Retrieve a specific book by its ID

GET /books/author - Retrieve a specific book by AUTHOR

GET /books/title - Retrieve a specific book by TITLE

GET /books/page - Retrive the No of Books from in 1 page

GET /books/sort/title - Sort the Books based on title Either in Asc or Desc based on cases

GET /books/sort/author - Sort the Books based on author Either in Asc or Desc based on cases

POST /books - Add a new book

PUT /books/:id - Update a specific book's details

DELETE /books/:id - Delete a specific book




