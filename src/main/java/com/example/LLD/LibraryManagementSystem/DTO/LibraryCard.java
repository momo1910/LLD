package com.example.LLD.LibraryManagementSystem.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class LibraryCard {
    private int CardNo;
    private Date DateOfIssue;
    private String IssuedBy;
    private String Barcode;
    private int memberId;

    public LibraryCard(int cardNo, Date dateOfIssue, String issuedBy, String barcode, int memberId) {
        CardNo = cardNo;
        DateOfIssue = dateOfIssue;
        IssuedBy = issuedBy;
        Barcode = barcode;
        this.memberId = memberId;
    }
}
