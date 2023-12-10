package com.example.LLD.LibraryManagementSystem.DTO;

import lombok.Data;

import java.util.List;
@Data
public class SearchCriteria {
    private String isbn;
    private List<String> author;
    private String title;
}
