package com.example.LLD.LibraryManagementSystem.DTO;

import java.util.Date;
import java.util.List;

public class Members extends  Person {
    private int memberId;
    private Date dateofJoining;
    private LibraryCard cardNo;
    private List<BookItem> bookIssued;
    private List<BookItem> reservedBook;
    private List<BookItem> returnedBook;

    public List<BookItem> getReturnedBook() {
        return returnedBook;
    }

    public void setReturnedBook(List<BookItem> returnedBook) {
        this.returnedBook = returnedBook;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getDateofJoining() {
        return dateofJoining;
    }

    public void setDateofJoining(Date dateofJoining) {
        this.dateofJoining = dateofJoining;
    }

    public LibraryCard getCardNo() {
        return cardNo;
    }

    public void setCardNo(LibraryCard cardNo) {
        this.cardNo = cardNo;
    }

    public List<BookItem> getBookIssued() {
        return bookIssued;
    }

    public void setBookIssued(List<BookItem> bookIssued) {
        this.bookIssued = bookIssued;
    }

    public List<BookItem> getReservedBook() {
        return reservedBook;
    }

    public void setReservedBook(List<BookItem> reservedBook) {
        this.reservedBook = reservedBook;
    }
}
