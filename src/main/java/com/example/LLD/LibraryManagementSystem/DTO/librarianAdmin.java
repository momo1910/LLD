package com.example.LLD.LibraryManagementSystem.DTO;

public interface librarianAdmin {
     void AddbookItem(BookItem bookItem);
     void DeletebookItem(BookItem bookItem, String barcode);
     void checkoutBookItem(Account account,BookItem bookItem);
     void renewBookItem(Account account,BookItem bookItem);
     void returnBookItem(Account account,BookItem bookItem);
     void createAccount();
     void deleteAccount(Account account);


}
