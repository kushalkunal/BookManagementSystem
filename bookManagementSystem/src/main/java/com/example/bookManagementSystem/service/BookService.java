package com.example.bookManagementSystem.service;
import com.example.bookManagementSystem.exception.BookNotFoundException;
import com.example.bookManagementSystem.exception.InvalidBookInputException;
import com.example.bookManagementSystem.model.Book;
import com.example.bookManagementSystem.repository.BookRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
@Service
public class BookService {
   
    @Autowired
    private BookRepository bookRepository;

    public  List<Book> getAllBook(){
        return bookRepository.findAll();
    }


    public Optional<Book> getById(int id) {
        return bookRepository.findById(id)
                .or(() -> {
                    throw new BookNotFoundException("Book with ID " + id + " not found.");
                });
    }

    public  Book addBook(  Book book){
        validateBookInput(book);
        return  bookRepository.save(book);
    }



    public Optional<Book> deleteById(int id) {
        Optional<Book> bookToDelete = bookRepository.findById(id);
        if (bookToDelete.isPresent()) {
            bookRepository.deleteById(id);
            return bookToDelete;
        } else {
            throw new BookNotFoundException("Book with ID " + id + " not found for deletion.");
        }
    }



    public Optional<Book> updateBook(Integer id,   Book updatedBook) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);

        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();


            // Update fields
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPrice(updatedBook.getPrice());

            // Save the updated entity
            return Optional.of(bookRepository.save(existingBook));
        } else {
            return Optional.empty();
        }
    }


    public List<Book> getByTitle(String title){

        List<Book> bookByID=bookRepository.findByTitle(title);
        if (bookByID.isEmpty()){
            throw  new RuntimeException("Book not found with this "+ title+" name ");
        }
        return  bookByID;

    }

    public List<Book> getByAuthor(String author){
        List<Book> bookByAuthor= bookRepository.findByAuthor(author);
        if(bookByAuthor.isEmpty()){
            throw new RuntimeException("Book not found from this author name "+author);
        }
        return bookByAuthor;
    }




    private void validateBookInput(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidBookInputException("Book title cannot be null or empty.");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new InvalidBookInputException("Book author cannot be null or empty.");
        }
        if (book.getPrice() <= 0) {
            throw new InvalidBookInputException("Book price must be greater than zero.");
        }
    }

    public Page<Book> getBooksPage(Pageable pageable) {
        return bookRepository.findAll(pageable); // Handles pagination and sorting
    }

    public List<Book> getBooksSorted(Sort sort) {
        return bookRepository.findAll(sort); // Fetch all books with dynamic sorting
    }

    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable); // Pagination and sorting combined
    }



}








