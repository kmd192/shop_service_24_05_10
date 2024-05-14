package com.example.shop.category;

import com.example.shop.merchandise.Merchandise;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String gender;

    private String clothType;

    private String season;

    @OneToMany(mappedBy = "category")
    private List<Merchandise> merchandiseList = new ArrayList<>();
}
