package com.example.LLD.LibraryManagementSystem.Repo;
import com.example.LLD.LibraryManagementSystem.DTO.Account;
import com.example.LLD.LibraryManagementSystem.DTO.Book;
import com.example.LLD.LibraryManagementSystem.DTO.BookItem;
import com.example.LLD.LibraryManagementSystem.Enums.BookStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepo {
    private Map<String,List<BookItem>> bookRepo=new HashMap<>();


    public void addBook(String isbn ,List<BookItem> bookItemList){
        bookRepo.put(isbn,bookItemList);
    }

    public void removeBook(BookItem bookItem, String barcode){
        if(barcode.isEmpty())
            bookRepo.remove(bookItem.getIsbn());
        else
        {
            for (BookItem bookitem:bookRepo.get(bookItem.getIsbn())) {
                 if(bookitem.getBarcode().equalsIgnoreCase(barcode))
                     bookitem.setBookStatus(BookStatus.LOST);
            }
        }
    }
    public boolean ifBookExists(String isbn)
    {
        List<BookItem> searchedBookItem=bookRepo.get(isbn);
        if(searchedBookItem.size()>=1)
            return true;
        else
            return false;
    }

    public List<BookItem> getbookDetails(String isbn){
        return bookRepo.get(isbn);
    }

    public List<BookItem> getbookDetailsbyTitle(String isbn){
        return null;
        //query to get book by authors
    }

    public List<BookItem> getbookDetailsbyAuthor(String isbn){
        return null;
        //query to get book by authors
    }


}
