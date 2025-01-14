package com.example.bookManagementSystem.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name="bookDb")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // for Auto id
    private Integer id;

//    @NotNull(message = "Title has been required")
    private String title;
//    @NotNull(message = "Author name has been required")
    private String author;
//    @Positive(message = "Price of book should be greater than zero")
    private double price;

    public Book(Integer id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book() {
    }

    // getter and setters for id
    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }
    // for title

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return  title;
    }
    // for author


    public void setAuthor(String author){
        this.author=author;
    }

    public String getAuthor(){
        return  author;
    }

    // for price

    public void setPrice(Double price){
        this.price=price;
    }

    public  double getPrice(){
        return  price;
    }

}

