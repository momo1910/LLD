package com.example.LLD.LibraryManagementSystem.DTO;

import lombok.Data;

@Data

public class Rack {
    int location;

    public Rack(int location) {
        this.location = location;
    }
}
