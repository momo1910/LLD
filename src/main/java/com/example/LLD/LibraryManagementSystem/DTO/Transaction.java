package com.example.LLD.LibraryManagementSystem.DTO;

import java.util.Date;

public class Transaction {
    private String transactioId;
    private Account account;
    private int fees;
    private String isbn;
    private Date paymentDate;
    private Date dueDate;
    private Date issueDate;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getFees() {
        return fees;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTransactioId() {
        return transactioId;
    }

    public void setTransactioId(String transactioId) {
        this.transactioId = transactioId;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
