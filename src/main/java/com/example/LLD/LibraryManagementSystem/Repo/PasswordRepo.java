package com.example.LLD.LibraryManagementSystem.Repo;

import java.util.List;
import java.util.Map;

import static ch.qos.logback.core.joran.JoranConstants.NULL;

public class PasswordRepo {
    private Map<Integer,String> userpwd;

    public void savePassword(int userid,String password){
      userpwd.put(userid,password);
    }

}
