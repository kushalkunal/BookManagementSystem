package com.example.bookManagementSystem.service;
import com.example.bookManagementSystem.model.Book;
import com.example.bookManagementSystem.service.BookService;
import com.example.bookManagementSystem.repository.BookRepository;
import com.example.bookManagementSystem.exception.BookNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
   private  BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Before
    public  void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addBookTest(){
        Book book= new Book(1,"Book One","Author One",23.00);
        when(bookRepository.save(book)).thenReturn(book);
        Book savedBook=bookService.addBook(book);
        assertNotNull(savedBook);
        assertEquals("Book One",book.getTitle());

    }

    @Test
    public void getAllBookTest(){
        Book book1 = new Book(1, "Book One", "Author A", 100);
        Book book2 = new Book(2, "Book Two", "Author B", 200);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1,book2));
        List<Book> books=bookService.getAllBook();
        assertNotNull(books);
        assertEquals(2,books.size());
        assertEquals("Book One",books.get(0).getTitle());
    }
    @Test

    public void getByIdTest(){
        Book book = new Book(1, "Book One", "Author A", 100.00);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Optional<Book> resultActual=bookService.getById(1);
        assertTrue(resultActual.isPresent());
        assertEquals("Book One",resultActual.get().getTitle());

    }

    @Test

    public void deleteByIdTest(){
        Book book = new Book(1, "Book One", "Author A", 100.00);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Optional<Book> deletedBook=bookService.deleteById(1);

        assertTrue(deletedBook.isPresent());
        assertEquals("Book One", deletedBook.get().getTitle());

    }

    @Test

    public  void getByAuthorTest(){
        Book book1 = new Book(1, "Book One", "Author A", 100.00);
        Book book2 = new Book(1, "Book Two", "Author A", 200.00);
        Book book3 = new Book(1, "Book Three", "Author C", 140.00);

        when(bookRepository.findByAuthor("Author A")).thenReturn(Arrays.asList(book1,book2));
        List<Book> books=bookService.getByAuthor("Author A");

        assertNotNull(books);
        assertEquals(2,books.size());
    }

    @Test
    public  void getByTitleTest(){
        Book book1 = new Book(1, "Book One", "Author A", 100.00);
        Book book2 = new Book(1, "Book Three", "Author A", 200.00);
        Book book3 = new Book(1, "Book Three", "Author C", 140.00);

        when(bookRepository.findByTitle("Book Three")).thenReturn(Arrays.asList(book2,book3));
        List<Book> books= bookService.getByTitle("Book Three");
        assertNotNull(books);
        assertEquals(2,books.size());
    }



}
