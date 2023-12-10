package com.example.LLD.LibraryManagementSystem.Repo;

import com.example.LLD.LibraryManagementSystem.DTO.Account;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WishedbookRepo {
    private Map<String, List<Account>> wishedbookRepo=new HashMap<>();
    public List<Account> saveWishList(String isbn, Account account){
        if(wishedbookRepo.get(isbn)==null)
            wishedbookRepo.put(isbn, Collections.singletonList(account));
        else{
            List<Account> accountList=wishedbookRepo.get(isbn);
            accountList.add(account);
            wishedbookRepo.put(isbn,accountList);
        }

        return wishedbookRepo.get(isbn);
    }

    public List<Account> getWishList(String isbn){
        return wishedbookRepo.get(isbn);
    }


}
