package com.example.shop.category;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String gender;

    private String clothType;

    private String clothMaterial;

    private String season;
}
