package com.example.bookManagementSystem.controller;

import com.example.bookManagementSystem.model.Book;
import com.example.bookManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")

public class BookController {
    @Autowired
    private BookService bookServices;

    @GetMapping
    public List<Book> getAllBook(){
        return bookServices.getAllBook();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = bookServices.getById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public List<Book> getAllBookByTitle(@PathVariable String title){
        return bookServices.getByTitle(title);
    }

    @GetMapping("/author/{author}")
    public List<Book> getAllBookByAuthor(@PathVariable String author){
        return bookServices.getByAuthor(author);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return  bookServices.addBook(book);
    }

    @PutMapping("/{id}")

    public Optional<Book> updateBook(@PathVariable int id, @RequestBody Book book){
        return bookServices.updateBook(id,book);
    }
    @DeleteMapping("/{id}")
    public  Optional<Book> deleteBook(@PathVariable int id){
        return  bookServices.deleteById(id);
    }


    @GetMapping("/page")
    public Page<Book> getBooksPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return bookServices.getBooksPage(pageable);
    }

    @GetMapping("/sort")
    public List<Book> getBooksSorted(
            @RequestParam(defaultValue = "id") String sortBy,  // Field to sort by (default "id")
            @RequestParam(defaultValue = "asc") String order   // Sorting direction (default "asc")
    )
    {
         Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);

         return bookServices.getBooksSorted(sort);
    }


    @GetMapping("/pageandsort")
    public Page<Book> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        // Parse the sort parameter
        String[] sortParams = sort.split(",");
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0])
        );


        return bookServices.getBooks(pageable);
    }


}
