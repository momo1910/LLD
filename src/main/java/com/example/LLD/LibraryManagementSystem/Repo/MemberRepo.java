package com.example.LLD.LibraryManagementSystem.Repo;

import com.example.LLD.LibraryManagementSystem.DTO.Members;

import java.util.ArrayList;
import java.util.List;

public class MemberRepo {

    List<Members> membersList = new ArrayList<>();
    public void addMembers(Members members){
        membersList.add(members);
    }

    public void removeMembers(Members members){
        membersList.remove(members);
    }
}
