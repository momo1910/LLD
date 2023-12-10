package com.example.LLD.LibraryManagementSystem.Repo;

import com.example.LLD.LibraryManagementSystem.DTO.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionRepo {
    List<Transaction> transactionRepoList= new ArrayList<>();
    public  void addTransaction(Transaction transaction)
    {
        transaction.setTransactioId(String.valueOf(UUID.randomUUID()));
        transactionRepoList.add(transaction);
    }
}
