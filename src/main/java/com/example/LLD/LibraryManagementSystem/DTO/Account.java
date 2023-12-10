package com.example.LLD.LibraryManagementSystem.DTO;

import com.example.LLD.LibraryManagementSystem.Enums.BookStatus;
import com.example.LLD.LibraryManagementSystem.Repo.BookRepo;
import com.example.LLD.LibraryManagementSystem.Repo.PasswordRepo;
import com.example.LLD.LibraryManagementSystem.Repo.WishedbookRepo;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Account implements MemberServices {
    private Members members;
    PasswordRepo passwordRepo = new PasswordRepo();
    BookRepo bookRepo = new BookRepo();
    WishedbookRepo wishedbookRepo = new WishedbookRepo();
    Librarian librarian = new Librarian();

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    private String accountId;

    public boolean resetPassWord(){
        System.out.println("Enter the New passowrd");
        Scanner sc = new Scanner(System.in);
        String password=sc.next();
        passwordRepo.savePassword(members.getMemberId(),password);
        return true;
    }

    public boolean savePassWord(){
        System.out.println("Enter the New passowrd");
        Scanner sc = new Scanner(System.in);
        String password=sc.next();
        passwordRepo.savePassword(members.getMemberId(),password);
        return true;
    }

    @Override
    public void searchBookByAuthor(String Author) {
        List<BookItem> searchBookList= bookRepo.getbookDetailsbyAuthor(Author);
        System.out.println(searchBookList);
    }

    @Override
    public void searchBookByIsbn(String isbn) {
        List<BookItem> searchBookList= bookRepo.getbookDetails(isbn);
        System.out.println(searchBookList);
    }

    @Override
    public void searchBookByTitle(String Title) {
        List<BookItem> searchBookList= bookRepo.getbookDetailsbyTitle(Title);
        System.out.println(searchBookList);

    }

    @Override
    public void issueBook(BookItem bookItemk) {
        librarian.checkoutBookItem(this,bookItemk);

    }

    @Override
    public void returnBook(BookItem bookItem) {
        librarian.returnBookItem(this,bookItem);

    }

    @Override
    public void renewBook(BookItem bookItem) {
        librarian.renewBookItem(this,bookItem);

    }

    @Override
    public void reserveBook(Book book) {
        List<BookItem> bookItemList=bookRepo.getbookDetails(book.getIsbn());
        Map<String,List<BookItem>> bookListStatus= librarian.getCurrentBookStatusCount(bookItemList);
        if(bookListStatus.get(BookStatus.AVAILABLE).size()==0){
            BookItem bookItem = new BookItem();
            bookItem.setIsbn(book.getIsbn());
            bookItem.setAuthor(book.getAuthor());
            bookItem.setTitle(book.getTitle());
            this.getMembers().getReservedBook().add(bookItem);
            wishedbookRepo.saveWishList(bookItem.getIsbn(),this);
        }

    }
}
