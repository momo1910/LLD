package com.example.LLD.LibraryManagementSystem.Repo;
import com.example.LLD.LibraryManagementSystem.DTO.Account;


import java.util.*;

public class AccountRepo {
    MemberRepo memberRepo = new MemberRepo();
    List<Account> accountList = new ArrayList<>();

    public void addAccount(Account account){
        accountList.add(account);
    }
    public  List<Account> getAccountList() {
        return  accountList;
    }

    public  void removeAccount(Account account) {
        accountList.remove(account);
        memberRepo.removeMembers(account.getMembers());

    }
}
