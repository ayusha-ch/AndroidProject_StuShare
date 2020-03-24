package com.example.stu_share;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookCoordinator {
    public final static List<Book> BOOK_LIST = new ArrayList<Book>();
    public final static List<Book> BOOK_PAST_LIST = new ArrayList<Book>();

    public static class Book implements Serializable {
        public String id;
        public String ownerId;
        public String title;
        public String author;
        public String edition;
        public String isbn;
        public String details;
        public String price;
        public String created;
        public String status;




        public Book(String id, String ownerId, String title, String author, String edition,
                    String isbn, String details, String price, String created, String status){
            this.id = id;
            this.ownerId = ownerId;
            this.title = title;
            this.author = author;
            this.edition = edition;
            this.isbn = isbn;
            this.details = details;
            this.price = price;
            this.created = created;
            this.status = status;
        }

        public String getId(){
            return id;
        }
        public String getOwnerId(){
            return ownerId;
        }
        public String getTitle(){
            return title;
        }
        public String getAuthor(){
            return author;
        }
        public String getEdition(){
            return edition;
        }
        public String getIsbn(){
            return isbn;
        }
        public String getDetails(){ return details; }
        public String getCreated(){
            return created;
        }
        public String getStatus(){
            return status;
        }
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }


        public void setId(String id) {
            this.id = id;
        }
        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public void setAuthor(String author){
            this.author = author;
        }
        public void setEdition(String edition) {
            this.edition = edition;
        }
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
        public void setDetails(String details) { this.details = details; }
        public void setCreated(String created) {
            this.created = created;
        }
        public void setStatus(String status) {
            this.status = status;
        }


        public String toString() {
            return title  ;
        }
    }
}
