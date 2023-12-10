package com.example.LLD.LibraryManagementSystem.DTO;

import com.example.LLD.LibraryManagementSystem.DTO.Book;
import com.example.LLD.LibraryManagementSystem.Enums.BookFormat;
import com.example.LLD.LibraryManagementSystem.Enums.BookStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookItem extends Book {
    private Rack rack;
    private BookStatus bookStatus;
    private  Date issuedate;
    private Date duedate;
    private BookFormat bookFormat;
    private boolean reference ;
    private String Barcode;


}
