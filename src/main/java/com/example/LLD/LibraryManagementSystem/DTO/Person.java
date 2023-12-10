package com.example.LLD.LibraryManagementSystem.DTO;

import lombok.Data;

@Data
public class Person {
    private String name;
    private String emailaddress;
    private String phoneno;
    private Address address;
}
