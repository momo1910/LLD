package com.example.LLD.LibraryManagementSystem.DTO;

public interface MemberServices {
    void searchBookByAuthor(String Author);
    void searchBookByIsbn(String Book);
    void searchBookByTitle(String Title);
    void issueBook(BookItem bookItemk);
    void returnBook(BookItem bookItem);
    void renewBook(BookItem bookItem);
    void reserveBook(Book book);
}
